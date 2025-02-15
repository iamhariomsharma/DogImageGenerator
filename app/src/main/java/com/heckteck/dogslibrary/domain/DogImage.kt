package com.heckteck.dogslibrary.domain

import androidx.annotation.Keep

@Keep
data class DogImage(
    val id: Long,
    val url: String,
    val timestamp: Long
)
