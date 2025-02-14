package com.heckteck.dogslibrary.data.repository

import com.heckteck.dogslibrary.core.network.util.DataResult
import com.heckteck.dogslibrary.core.network.util.executeApi
import com.heckteck.dogslibrary.core.network.util.map
import com.heckteck.dogslibrary.data.remote.DogImageApi
import com.heckteck.dogslibrary.domain.DogImageRepository

class DogImageRepositoryImpl(
    private val dogImageApi: DogImageApi
): DogImageRepository {

    override suspend fun getRandomDogImage(): DataResult<String?> {
        val response = executeApi { dogImageApi.getRandomDogImage() }

        return response.map { baseResponse ->
            baseResponse?.message
        }
    }
}