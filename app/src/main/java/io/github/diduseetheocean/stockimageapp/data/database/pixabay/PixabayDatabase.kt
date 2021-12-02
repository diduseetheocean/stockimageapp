package io.github.diduseetheocean.stockimageapp.data.database.pixabay

import android.content.Context
import androidx.room.*
import io.github.diduseetheocean.stockimageapp.data.api.pixabay.PixabayImage

@Database(entities = [PixabayImage::class], version = 1)
abstract class PixabayAppDatabase : RoomDatabase() {

    abstract fun pixabayImagesDao(): PixabayImageDao
}

@Dao
interface PixabayImageDao {
    @Insert
    suspend fun insertAll(images: List<PixabayImage>)

    @Query("SELECT * FROM PixabayImage")
    suspend fun getAllImages(): List<PixabayImage>

    @Query("DELETE FROM PixabayImage")
    suspend fun deleteAll()
}

fun createPixabayDatabase(context: Context) = Room.databaseBuilder(
    context,
    PixabayAppDatabase::class.java, "pixabay-database"
).build()