package com.heckteck.dogslibrary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DogImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogImage(dogImage: DogImageEntity): Long

    @Query("SELECT * FROM dog_images ORDER BY timestamp DESC LIMIT 20")
    fun getLatestDogImages(): Flow<List<DogImageEntity>>

    @Query("DELETE FROM dog_images")
    suspend fun clearAllDogImages()
}