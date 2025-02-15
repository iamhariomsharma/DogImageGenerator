package com.heckteck.dogslibrary.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DogImageDto(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: String? = null,
)
