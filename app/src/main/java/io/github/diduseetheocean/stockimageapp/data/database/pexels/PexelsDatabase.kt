package io.github.diduseetheocean.stockimageapp.data.database.pexels

import android.content.Context
import androidx.room.*
import io.github.diduseetheocean.stockimageapp.data.api.pexels.PexelsImage
import io.github.diduseetheocean.stockimageapp.data.api.pexels.Src
import org.json.JSONObject

@Database(entities = [PexelsImage::class], version = 1)
@TypeConverters(SrcTypeConverter::class)
abstract class PexelsAppDatabase : RoomDatabase() {

    abstract fun pexelsImagesDao(): PexelsImageDao
}

@Dao
interface PexelsImageDao {
    @Insert
    suspend fun insertAll(images: List<PexelsImage>)

    @Query("SELECT * FROM PexelsImage")
    suspend fun getAllImages(): List<PexelsImage>

    @Query("DELETE FROM PexelsImage")
    suspend fun deleteAll()
}

fun createPexelsDatabase(context: Context) = Room.databaseBuilder(
    context,
    PexelsAppDatabase::class.java, "pexels-database"
).build()

class SrcTypeConverter {
    @TypeConverter
    fun fromSource(src: Src): String {
        return JSONObject().apply {
            put("id", src.id)
            put("original", src.original)
            put("large2x", src.large2x)
            put("large", src.large)
            put("medium", src.medium)
            put("small", src.small)
            put("portrait", src.portrait)
            put("landscape", src.landscape)
            put("tiny", src.tiny)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): Src {
        val json = JSONObject(source)
        return Src(
            json.getInt("id"),
            json.getString("original"),
            json.getString("large2x"),
            json.getString("large"),
            json.getString("medium"),
            json.getString("small"),
            json.getString("portrait"),
            json.getString("landscape"),
            json.getString("tiny"),
        )
    }
}