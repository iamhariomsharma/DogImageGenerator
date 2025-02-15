package com.heckteck.dogslibrary.di

import androidx.room.Room
import com.heckteck.dogslibrary.BuildConfig
import com.heckteck.dogslibrary.core.util.AppConstants
import com.heckteck.dogslibrary.data.local.DogImageDao
import com.heckteck.dogslibrary.data.local.DogImageDatabase
import com.heckteck.dogslibrary.data.remote.DogImageApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {
    single<OkHttpClient> {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            clientBuilder.addInterceptor(logging)
        }
        clientBuilder.build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<DogImageApi> {
        get<Retrofit>().create<DogImageApi>()
    }

    single<DogImageDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            DogImageDatabase::class.java,
            AppConstants.DATABASE_NAME
        ).build()
    }

    single<DogImageDao> {
        get<DogImageDatabase>().dogImageDao()
    }
}