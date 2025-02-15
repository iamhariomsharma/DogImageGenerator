package com.heckteck.dogslibrary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DogImageEntity::class],
    version = 1
)
abstract class DogImageDatabase: RoomDatabase() {
    abstract fun dogImageDao(): DogImageDao
}