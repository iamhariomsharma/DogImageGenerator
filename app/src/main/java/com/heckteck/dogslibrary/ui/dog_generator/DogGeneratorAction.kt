package com.heckteck.dogslibrary.ui.dog_generator

sealed interface DogGeneratorAction {
    data object OnGenerateDogImage: DogGeneratorAction
}