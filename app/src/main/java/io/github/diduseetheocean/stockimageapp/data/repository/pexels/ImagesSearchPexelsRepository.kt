package io.github.diduseetheocean.stockimageapp.data.repository.pexels

import io.github.diduseetheocean.stockimageapp.data.api.pexels.PexelsApi
import io.github.diduseetheocean.stockimageapp.data.api.pexels.toImageModel
import io.github.diduseetheocean.stockimageapp.data.model.ImageModel
import io.github.diduseetheocean.stockimageapp.data.repository.ImagesSearchPexelsRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class ImagesSearchPexelsRepository @Inject constructor(
    private val remote: PexelsApi,
    private val local: ImagesSavePexelsRepository,
) : ImagesSearchPexelsRepositoryInterface {

    override fun search(query: String): Flow<List<ImageModel>> =
        flow {
            try {
                remote.search(query).photos?.let {
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