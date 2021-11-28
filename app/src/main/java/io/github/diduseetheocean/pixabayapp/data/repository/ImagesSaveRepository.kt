package io.github.diduseetheocean.pixabayapp.data.repository

import io.github.diduseetheocean.pixabayapp.data.api.pixabay.PixabayImage

interface ImagesSaveRepository {
    suspend fun getAllImage(): List<PixabayImage>

    suspend fun saveImages(images: List<PixabayImage>)

    suspend fun deleteAllImages()
}