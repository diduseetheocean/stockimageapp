package io.github.diduseetheocean.stockimageapp.data.api.pexels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import io.github.diduseetheocean.stockimageapp.data.model.ImageModel

@Entity
data class PexelsImage(
    @PrimaryKey
    @field:Json(name = "id")
    val id: Int? = 0,
    @field:Json(name = "width")
    val width: Int? = 0,
    @field:Json(name = "height")
    val height: Int? = 0,
    @field:Json(name = "url")
    val url: String? = "",
    @field:Json(name = "photographer")
    val photographer: String? = "",
    @field:Json(name = "photographer_url")
    val photographerUrl: String? = "",
    @field:Json(name = "photographer_id")
    val photographerId: String? = "",
    @field:Json(name = "avg_color")
    val avgColor: String? = "",
    @field:Json(name = "src")
    val src: Src? = Src(),
    @field:Json(name = "liked")
    val liked: Boolean? = false
)

@Entity
data class Src(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    @field:Json(name = "original")
    val original: String? = "",
    @field:Json(name = "large2x")
    val large2x: String? = "",
    @field:Json(name = "large")
    val large: String? = "",
    @field:Json(name = "medium")
    val medium: String? = "",
    @field:Json(name = "small")
    val small: String? = "",
    @field:Json(name = "portrait")
    val portrait: String? = "",
    @field:Json(name = "landscape")
    val landscape: String? = "",
    @field:Json(name = "tiny")
    val tiny: String? = ""
)

fun PexelsImage.toImageModel() = ImageModel(
    imageId = id?.toLong() ?: -1,
    userName = photographer ?: "",
    userImageURL = "",
    url = src?.small ?: "",
    likes = "",
    downloads = "",
    comments = "",
    tags = emptyList(),
    largeImageURL = src?.large2x
)