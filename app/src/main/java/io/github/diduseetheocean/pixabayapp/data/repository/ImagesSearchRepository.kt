package io.github.diduseetheocean.pixabayapp.data.repository

import io.github.diduseetheocean.pixabayapp.data.model.ImageModel
import kotlinx.coroutines.flow.Flow

interface ImagesSearchRepository {
    fun search(query: String): Flow<List<ImageModel>>
}