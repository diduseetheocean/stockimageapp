package io.github.diduseetheocean.stockimageapp.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.diduseetheocean.stockimageapp.data.api.flickr.FlickrApi
import io.github.diduseetheocean.stockimageapp.data.api.flickr.flickrApi
import io.github.diduseetheocean.stockimageapp.data.api.pexels.PexelsApi
import io.github.diduseetheocean.stockimageapp.data.api.pexels.pexelsApi
import io.github.diduseetheocean.stockimageapp.data.api.pixabay.PixabayApi
import io.github.diduseetheocean.stockimageapp.data.api.pixabay.pixabayApi
import io.github.diduseetheocean.stockimageapp.data.database.flickr.FlickrAppDatabase
import io.github.diduseetheocean.stockimageapp.data.database.flickr.FlickrImageDao
import io.github.diduseetheocean.stockimageapp.data.database.flickr.createFlickrDatabase
import io.github.diduseetheocean.stockimageapp.data.database.pexels.PexelsAppDatabase
import io.github.diduseetheocean.stockimageapp.data.database.pexels.PexelsImageDao
import io.github.diduseetheocean.stockimageapp.data.database.pexels.createPexelsDatabase
import io.github.diduseetheocean.stockimageapp.data.database.pixabay.PixabayAppDatabase
import io.github.diduseetheocean.stockimageapp.data.database.pixabay.PixabayImageDao
import io.github.diduseetheocean.stockimageapp.data.database.pixabay.createPixabayDatabase
import io.github.diduseetheocean.stockimageapp.data.repository.ImagesSearchFlickrRepositoryInterface
import io.github.diduseetheocean.stockimageapp.data.repository.ImagesSearchPexelsRepositoryInterface
import io.github.diduseetheocean.stockimageapp.data.repository.ImagesSearchPixabayRepositoryInterface
import io.github.diduseetheocean.stockimageapp.data.repository.flickr.ImagesSaveFlickrRepository
import io.github.diduseetheocean.stockimageapp.data.repository.flickr.ImagesSearchFlickrRepository
import io.github.diduseetheocean.stockimageapp.data.repository.pexels.ImagesSavePexelsRepository
import io.github.diduseetheocean.stockimageapp.data.repository.pexels.ImagesSearchPexelsRepository
import io.github.diduseetheocean.stockimageapp.data.repository.pixabay.ImagesSavePixabayRepository
import io.github.diduseetheocean.stockimageapp.data.repository.pixabay.ImagesSearchPixabayRepository
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
    @Singleton
    fun providesPexelsRemote(): PexelsApi {
        return pexelsApi()
    }

    @Provides
    @Singleton
    fun providesFlickrRemote(): FlickrApi {
        return flickrApi()
    }

    @Provides
    @Singleton
    fun providesPixabayImageRepository(repository: ImagesSearchPixabayRepository): ImagesSearchPixabayRepositoryInterface {
        return repository
    }

    @Provides
    @Singleton
    fun providesPexelsImageRepository(repository: ImagesSearchPexelsRepository): ImagesSearchPexelsRepositoryInterface {
        return repository
    }

    @Provides
    @Singleton
    fun providesFlickrImageRepository(repository: ImagesSearchFlickrRepository): ImagesSearchFlickrRepositoryInterface {
        return repository
    }

    @Provides
    @Singleton
    fun providesPixabayImagesDao(pixabayAppDatabase: PixabayAppDatabase): PixabayImageDao {
        return pixabayAppDatabase.pixabayImagesDao()
    }

    @Provides
    @Singleton
    fun providesPexelsImagesDao(pexelsAppDatabase: PexelsAppDatabase): PexelsImageDao {
        return pexelsAppDatabase.pexelsImagesDao()
    }

    @Provides
    @Singleton
    fun providesFlickrImagesDao(flickrAppDatabase: FlickrAppDatabase): FlickrImageDao {
        return flickrAppDatabase.flickrImagesDao()
    }

    @Provides
    @Singleton
    fun providesPixabayLocal(daoPixabay: PixabayImageDao): ImagesSavePixabayRepository {
        return ImagesSavePixabayRepository(daoPixabay)
    }

    @Provides
    @Singleton
    fun providesPexelsLocal(daoPexels: PexelsImageDao): ImagesSavePexelsRepository {
        return ImagesSavePexelsRepository(daoPexels)
    }

    @Provides
    @Singleton
    fun providesFlickrLocal(daoFlickr: FlickrImageDao): ImagesSaveFlickrRepository {
        return ImagesSaveFlickrRepository(daoFlickr)
    }

    @Provides
    @Singleton
    fun providesPixabayDatabase(@ApplicationContext context: Context): PixabayAppDatabase {
        return createPixabayDatabase(context)
    }

    @Provides
    @Singleton
    fun providesPexelsDatabase(@ApplicationContext context: Context): PexelsAppDatabase {
        return createPexelsDatabase(
            context
        )
    }

    @Provides
    @Singleton
    fun providesFlickrDatabase(@ApplicationContext context: Context): FlickrAppDatabase {
        return createFlickrDatabase(
            context
        )
    }
}