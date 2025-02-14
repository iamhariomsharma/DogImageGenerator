package com.heckteck.dogslibrary.data.remote

import com.heckteck.dogslibrary.data.remote.dto.DogImageDto
import retrofit2.Response
import retrofit2.http.GET

interface DogImageApi {

    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<DogImageDto>
}