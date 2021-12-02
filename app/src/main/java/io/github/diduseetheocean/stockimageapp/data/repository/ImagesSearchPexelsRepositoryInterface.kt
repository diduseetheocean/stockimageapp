package io.github.diduseetheocean.stockimageapp.data.repository

import io.github.diduseetheocean.stockimageapp.data.model.ImageModel
import kotlinx.coroutines.flow.Flow

interface ImagesSearchPixabayRepositoryInterface {
    fun search(query: String): Flow<List<ImageModel>>
}