package com.heckteck.dogslibrary.domain

import com.heckteck.dogslibrary.core.network.util.DataResult
import kotlinx.coroutines.flow.Flow

interface DogImageRepository {
    suspend fun getRandomDogImage(): DataResult<DogImage?>
    fun getCachedImages(): Flow<List<DogImage>>
    suspend fun clearAllDogImages()
}