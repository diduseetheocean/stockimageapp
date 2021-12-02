package io.github.diduseetheocean.stockimageapp.data.repository.pixabay

import io.github.diduseetheocean.stockimageapp.data.api.pixabay.PixabayApi
import io.github.diduseetheocean.stockimageapp.data.api.pixabay.toImageModel
import io.github.diduseetheocean.stockimageapp.data.model.ImageModel
import io.github.diduseetheocean.stockimageapp.data.repository.ImagesSearchPixabayRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class ImagesSearchPixabayRepository @Inject constructor(
    private val remote: PixabayApi,
    private val local: ImagesSavePixabayRepository,
) : ImagesSearchPixabayRepositoryInterface {

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