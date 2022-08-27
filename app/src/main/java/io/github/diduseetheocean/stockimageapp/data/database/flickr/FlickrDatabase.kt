package io.github.diduseetheocean.stockimageapp.data.database.flickr


import android.content.Context
import androidx.room.*
import io.github.diduseetheocean.stockimageapp.data.api.flickr.FlickrPhoto

@Database(entities = [FlickrPhoto::class], version = 1)
abstract class FlickrAppDatabase : RoomDatabase() {

    abstract fun flickrImagesDao(): FlickrImageDao
}

@Dao
interface FlickrImageDao {
    @Insert
    suspend fun insertAll(images: List<FlickrPhoto>)

    @Query("SELECT * FROM FlickrPhoto")
    suspend fun getAllImages(): List<FlickrPhoto>

    @Query("DELETE FROM FlickrPhoto")
    suspend fun deleteAll()
}

fun createFlickrDatabase(context: Context) = Room.databaseBuilder(
    context,
    FlickrAppDatabase::class.java, "flickr-database"
).build()