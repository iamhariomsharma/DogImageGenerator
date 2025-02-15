package com.heckteck.dogslibrary.ui.dog_generator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heckteck.dogslibrary.core.network.util.DataResult
import com.heckteck.dogslibrary.domain.DogImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DogGeneratorViewModel(
    private val repository: DogImageRepository,
): ViewModel() {

    private val _state = MutableStateFlow(DogGeneratorState())
    val state = _state.asStateFlow()

    fun onAction(action: DogGeneratorAction) {
        when (action) {
            DogGeneratorAction.OnGenerateDogImage -> {
                generateDogImage()
            }
        }
    }

    private fun generateDogImage() {
        viewModelScope.launch {
            _state.update { it.copy(
                isGenerating = true,
                errorMessage = null
            ) }
            when (val result = repository.getRandomDogImage()) {
                is DataResult.Success -> {
                    _state.update { it.copy(
                        isGenerating = false,
                        imageUrl = result.data?.url
                    ) }
                }
                is DataResult.Error -> {
                    _state.update { it.copy(
                        isGenerating = false,
                        errorMessage = result.error
                    ) }
                }
            }
        }
    }
}