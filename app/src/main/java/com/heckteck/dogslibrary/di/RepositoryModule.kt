package com.heckteck.dogslibrary.di

import com.heckteck.dogslibrary.data.repository.DogImageRepositoryImpl
import com.heckteck.dogslibrary.domain.DogImageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
    singleOf(::DogImageRepositoryImpl).bind<DogImageRepository>()
}