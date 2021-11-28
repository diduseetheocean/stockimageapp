package io.github.diduseetheocean.pixabayapp.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.diduseetheocean.pixabayapp.data.api.pixabay.PixabayApi
import io.github.diduseetheocean.pixabayapp.data.api.pixabay.pixabayApi
import io.github.diduseetheocean.pixabayapp.data.database.AppDatabase
import io.github.diduseetheocean.pixabayapp.data.database.ImageDao
import io.github.diduseetheocean.pixabayapp.data.database.createDatabase
import io.github.diduseetheocean.pixabayapp.data.repository.ImagesSavePixabayRepository
import io.github.diduseetheocean.pixabayapp.data.repository.ImagesSearchPixabayRepository
import io.github.diduseetheocean.pixabayapp.data.repository.ImagesSearchRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun providesPixapayRemote(): PixabayApi {
        return pixabayApi()
    }

    @Provides
    fun providesImageRepository(repository: ImagesSearchPixabayRepository): ImagesSearchRepository {
        return repository
    }

    @Provides
    @Singleton
    fun providesImagesDao(appDatabase: AppDatabase): ImageDao {
        return appDatabase.imagesDao()
    }

    @Provides
    @Singleton
    fun providesPixabayLocal(dao: ImageDao): ImagesSavePixabayRepository {
        return ImagesSavePixabayRepository(dao)
    }

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return createDatabase(context)
    }
}