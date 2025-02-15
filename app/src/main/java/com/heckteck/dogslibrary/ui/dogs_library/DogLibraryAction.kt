package com.heckteck.dogslibrary.ui.dogs_library

sealed interface DogLibraryAction {
    data object OnClearAllDogImages: DogLibraryAction
}