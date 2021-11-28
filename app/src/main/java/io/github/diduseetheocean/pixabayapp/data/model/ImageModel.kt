package io.github.diduseetheocean.pixabayapp.data.model

data class ImageModel(
    val imageId: Long = -1,
    val userName: String,
    val userImageURL: String,
    val url: String,
    val likes: String,
    val downloads: String,
    val comments: String,
    val tags: List<String>,
    val largeImageURL: String?,
)