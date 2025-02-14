package com.heckteck.dogslibrary.domain

import com.heckteck.dogslibrary.core.network.util.DataResult

interface DogImageRepository {
    suspend fun getRandomDogImage(): DataResult<String?>
}