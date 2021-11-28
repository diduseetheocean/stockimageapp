package io.github.diduseetheocean.pixabayapp.data.repository

import io.github.diduseetheocean.pixabayapp.data.api.pixabay.PixabayApi
import io.github.diduseetheocean.pixabayapp.data.api.pixabay.toImageModel
import io.github.diduseetheocean.pixabayapp.data.model.ImageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class ImagesSearchPixabayRepository @Inject constructor(
    private val remote: PixabayApi,
    private val local: ImagesSavePixabayRepository,
) : ImagesSearchRepository {

    override fun search(query: String): Flow<List<ImageModel>> =
        flow {
            try {
                remote.search(query).images?.let {
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