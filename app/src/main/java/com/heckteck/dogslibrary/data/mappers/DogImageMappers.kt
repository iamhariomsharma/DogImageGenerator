package com.heckteck.dogslibrary.data.mappers

import com.heckteck.dogslibrary.data.local.DogImageEntity
import com.heckteck.dogslibrary.data.remote.dto.DogImageDto
import com.heckteck.dogslibrary.domain.DogImage

fun DogImageDto.toDogEntity(): DogImageEntity {
    return DogImageEntity(url = message)
}

fun DogImageEntity.toDogImage(): DogImage {
    return DogImage(
        id = id,
        url = url,
        timestamp = timestamp
    )
}