package io.github.diduseetheocean.stockimageapp.data.repository.pixabay

import io.github.diduseetheocean.stockimageapp.data.api.pixabay.PixabayImage
import io.github.diduseetheocean.stockimageapp.data.database.pixabay.PixabayImageDao
import javax.inject.Inject

class ImagesSavePixabayRepository @Inject constructor(
    private val pixabayImageDao: PixabayImageDao,
) {
    suspend fun getAllImage(): List<PixabayImage> {
        return pixabayImageDao.getAllImages()
    }

    suspend fun saveImages(images: List<PixabayImage>) {
        return pixabayImageDao.insertAll(images)
    }

    suspend fun deleteAllImages() {
        pixabayImageDao.deleteAll()
    }
}