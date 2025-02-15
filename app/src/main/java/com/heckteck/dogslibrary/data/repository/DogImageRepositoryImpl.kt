package com.heckteck.dogslibrary.data.repository

import android.util.LruCache
import com.heckteck.dogslibrary.core.network.util.DataResult
import com.heckteck.dogslibrary.core.network.util.executeApi
import com.heckteck.dogslibrary.core.util.AppConstants
import com.heckteck.dogslibrary.data.local.DogImageDao
import com.heckteck.dogslibrary.data.local.DogImageEntity
import com.heckteck.dogslibrary.data.mappers.toDogEntity
import com.heckteck.dogslibrary.data.mappers.toDogImage
import com.heckteck.dogslibrary.data.remote.DogImageApi
import com.heckteck.dogslibrary.domain.DogImage
import com.heckteck.dogslibrary.domain.DogImageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DogImageRepositoryImpl(
    private val dogImageApi: DogImageApi,
    private val dogImageDao: DogImageDao,
    private val ioDispatcher: CoroutineDispatcher
) : DogImageRepository {

    private val lruCache = object : LruCache<Long, DogImageEntity>(20) {
        override fun entryRemoved(
            evicted: Boolean,
            key: Long?,
            oldValue: DogImageEntity?,
            newValue: DogImageEntity?,
        ) {
            super.entryRemoved(evicted, key, oldValue, newValue)
        }
    }

    private val _cachedImagesFlow = MutableStateFlow<List<DogImage>>(emptyList())

    init {
        CoroutineScope(ioDispatcher).launch {
            dogImageDao.getLatestDogImages()
                .collect { images ->
                    lruCache.evictAll()
                    images.forEach { image ->
                        lruCache.put(image.id, image)
                    }
                    updateCachedImagesFlow()
                }
        }
    }

    override suspend fun getRandomDogImage(): DataResult<DogImage?> {
        val result = executeApi { dogImageApi.getRandomDogImage() }

        return when (result) {
            is DataResult.Success -> {
                val dogImageDto = result.data
                if (dogImageDto?.status == "success") {
                    val dogImageEntity = dogImageDto.toDogEntity()
                    val generatedId = dogImageDao.insertDogImage(dogImageEntity)
                    val savedDogEntity = dogImageEntity.copy(id = generatedId)
                    addToCache(savedDogEntity)
                    DataResult.Success(dogImageEntity.toDogImage())
                } else {
                    DataResult.Error(AppConstants.Errors.NO_DATA_FOUND)
                }
            }

            is DataResult.Error -> {
                DataResult.Error(result.error ?: AppConstants.Errors.UNKNOWN)
            }
        }
    }

    override fun getCachedImages(): Flow<List<DogImage>> {
        val cachedEntities = dogImageDao.getLatestDogImages()
        return cachedEntities
            .map { entities ->
                entities.map { it.toDogImage() }
            }
    }

    override suspend fun clearAllDogImages() {
        dogImageDao.clearAllDogImages()
        lruCache.evictAll()
        _cachedImagesFlow.value = emptyList()
    }

    private fun addToCache(entity: DogImageEntity) {
        lruCache.put(entity.id, entity)
        updateCachedImagesFlow()
    }

    private fun updateCachedImagesFlow() {
        val cachedImages = lruCache.snapshot().values
            .toList()
            .sortedByDescending { it.timestamp }
            .map { it.toDogImage() }
        _cachedImagesFlow.value = cachedImages
    }
}