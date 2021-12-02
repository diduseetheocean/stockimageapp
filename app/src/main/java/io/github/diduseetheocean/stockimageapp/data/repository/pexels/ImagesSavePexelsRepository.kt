package io.github.diduseetheocean.stockimageapp.data.repository.pexels

import io.github.diduseetheocean.stockimageapp.data.api.pexels.PexelsImage
import io.github.diduseetheocean.stockimageapp.data.database.pexels.PexelsImageDao
import javax.inject.Inject

class ImagesSavePexelsRepository @Inject constructor(
    private val pexelsImageDao: PexelsImageDao,
) {
    suspend fun getAllImage(): List<PexelsImage> {
        return pexelsImageDao.getAllImages()
    }

    suspend fun saveImages(images: List<PexelsImage>) {
        return pexelsImageDao.insertAll(images)
    }

    suspend fun deleteAllImages() {
        pexelsImageDao.deleteAll()
    }
}