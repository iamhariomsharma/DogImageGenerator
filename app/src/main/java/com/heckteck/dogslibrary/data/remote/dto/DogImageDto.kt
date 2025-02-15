package com.heckteck.dogslibrary.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DogImageDto(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: String? = null,
)
