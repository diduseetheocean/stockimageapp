package io.github.diduseetheocean.pixabayapp.data.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.diduseetheocean.pixabayapp.data.api.pixabay.PixabayImage

@Database(entities = [PixabayImage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun imagesDao(): ImageDao
}

@Dao
interface ImageDao {
    @Insert
    suspend fun insertAll(images: List<PixabayImage>)

    @Query("SELECT * FROM PixabayImage")
    suspend fun getAllImages(): List<PixabayImage>

    @Query("DELETE FROM PixabayImage")
    suspend fun deleteAll()
}

fun createDatabase(context: Context) = Room.databaseBuilder(
    context,
    AppDatabase::class.java, "pixabay-database"
).build()