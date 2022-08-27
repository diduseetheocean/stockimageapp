package io.github.diduseetheocean.stockimageapp.data.repository.flickr

import io.github.diduseetheocean.stockimageapp.data.api.flickr.FlickrApi
import io.github.diduseetheocean.stockimageapp.data.api.flickr.toImageModel
import io.github.diduseetheocean.stockimageapp.data.model.ImageModel
import io.github.diduseetheocean.stockimageapp.data.repository.ImagesSearchFlickrRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class ImagesSearchFlickrRepository @Inject constructor(
    private val remote: FlickrApi,
    private val local: ImagesSaveFlickrRepository,
) : ImagesSearchFlickrRepositoryInterface {
    override fun search(query: String): Flow<List<ImageModel>> =
        flow {
            try {
                remote.search(text = query).photos?.photo.let {
                    if (it.isNotEmpty()) {
                        local.deleteAllImages()
                        local.saveImages(it)
                    }
                }
            } catch (e: Exception) {
                Timber.e("Exception: $e")
            }
            emit(local.getAllImage().map {
                it.toImageModel()
            })
        }
}