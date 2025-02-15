package com.heckteck.dogslibrary.data.local

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity("dog_images")
data class DogImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val url: String,
    val timestamp: Long = System.currentTimeMillis()
)
