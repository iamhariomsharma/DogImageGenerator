package com.heckteck.dogslibrary.ui.dog_generator

data class DogGeneratorState(
    val imageUrl: String? = null,
    val errorMessage: String? = null,
    val isGenerating: Boolean = false,
)
