package com.heckteck.dogslibrary.ui.dogs_library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heckteck.dogslibrary.domain.DogImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DogLibraryViewModel(
    private val repository: DogImageRepository
): ViewModel() {

    private val _state = MutableStateFlow(DogLibraryState())
    val state = _state.asStateFlow()

    init {
        getCachedDogsImages()
    }

    fun onAction(action: DogLibraryAction) {
        when (action) {
            DogLibraryAction.OnClearAllDogImages -> {
                clearAllDogImages()
            }
        }
    }

    private fun getCachedDogsImages() {
        repository.getCachedImages()
            .onEach { dogImages ->
                _state.update { it.copy(dogsImages = dogImages) }
            }
            .launchIn(viewModelScope)
    }

    private fun clearAllDogImages() {
        viewModelScope.launch {
            repository.clearAllDogImages()
        }
    }
}