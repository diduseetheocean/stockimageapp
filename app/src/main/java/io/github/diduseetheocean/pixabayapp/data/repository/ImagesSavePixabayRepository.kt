package io.github.diduseetheocean.pixabayapp.data.repository

import io.github.diduseetheocean.pixabayapp.data.api.pixabay.PixabayImage
import io.github.diduseetheocean.pixabayapp.data.database.ImageDao
import javax.inject.Inject

class ImagesSavePixabayRepository @Inject constructor(
    private val imageDao: ImageDao,
) {
    suspend fun getAllImage(): List<PixabayImage> {
        return imageDao.getAllImages()
    }

    suspend fun saveImages(images: List<PixabayImage>) {
        return imageDao.insertAll(images)
    }

    suspend fun deleteAllImages() {
        imageDao.deleteAll()
    }
}