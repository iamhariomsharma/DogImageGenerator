package com.heckteck.dogslibrary.core.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status") val status: String? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("code") val code: Int? = null,
)
