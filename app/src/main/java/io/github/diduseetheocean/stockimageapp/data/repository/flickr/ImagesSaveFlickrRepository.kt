package io.github.diduseetheocean.stockimageapp.data.repository.flickr

import io.github.diduseetheocean.stockimageapp.data.api.flickr.FlickrPhoto
import io.github.diduseetheocean.stockimageapp.data.database.flickr.FlickrImageDao
import javax.inject.Inject

class ImagesSaveFlickrRepository  @Inject constructor(
    private val flickrImageDao: FlickrImageDao,
) {
    suspend fun getAllImage(): List<FlickrPhoto> {
        return flickrImageDao.getAllImages()
    }

    suspend fun saveImages(images: List<FlickrPhoto>) {
        return flickrImageDao.insertAll(images)
    }

    suspend fun deleteAllImages() {
        flickrImageDao.deleteAll()
    }
}