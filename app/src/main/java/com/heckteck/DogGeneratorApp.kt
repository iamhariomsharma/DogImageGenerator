package com.heckteck

import android.app.Application
import com.heckteck.dogslibrary.di.appModule
import com.heckteck.dogslibrary.di.repositoryModule
import com.heckteck.dogslibrary.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DogGeneratorApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DogGeneratorApp)
            modules(appModule, repositoryModule, viewModelModule)
        }
    }
}