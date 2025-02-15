package com.heckteck.dogslibrary.ui.dogs_library

import com.heckteck.dogslibrary.domain.DogImage

data class DogLibraryState(
    val dogsImages: List<DogImage> = emptyList()
)
