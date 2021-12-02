package io.github.diduseetheocean.stockimageapp.data.api.pexels

import com.squareup.moshi.Json

data class PexelsSearchResponse(
    @field:Json(name = "total_results")
    val totalResults: Int? = 0,
    @field:Json(name = "page")
    val page: Int? = 0,
    @field:Json(name = "per_page")
    val perPage: Int? = 0,
    @field:Json(name = "next_page")
    val nextPage: String? = "",
    @field:Json(name = "photos")
    val photos: List<PexelsImage>? = listOf()
)