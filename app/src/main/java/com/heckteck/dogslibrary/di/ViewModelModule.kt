package com.heckteck.dogslibrary.di

import com.heckteck.dogslibrary.ui.dog_generator.DogGeneratorViewModel
import com.heckteck.dogslibrary.ui.dogs_library.DogLibraryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::DogGeneratorViewModel)
    viewModelOf(::DogLibraryViewModel)
}