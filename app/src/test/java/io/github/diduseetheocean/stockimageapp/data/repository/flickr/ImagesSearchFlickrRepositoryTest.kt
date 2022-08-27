package io.github.diduseetheocean.stockimageapp.data.repository.flickr

import io.github.diduseetheocean.stockimageapp.data.api.flickr.FlickrApi
import io.github.diduseetheocean.stockimageapp.data.api.flickr.FlickrPhoto
import io.github.diduseetheocean.stockimageapp.data.api.flickr.FlickrSearchResultsWrapper
import io.github.diduseetheocean.stockimageapp.data.api.flickr.toImageModel
import io.github.diduseetheocean.stockimageapp.data.api.pexels.PexelsApi
import io.github.diduseetheocean.stockimageapp.data.api.pexels.PexelsImage
import io.github.diduseetheocean.stockimageapp.data.api.pexels.PexelsSearchResponse
import io.github.diduseetheocean.stockimageapp.data.api.pexels.toImageModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ImagesSearchFlickrRepositoryTest {

    @Test
    fun `Fetching results from Remote THEN caching results THEN comparing cache with results`() =
        runBlocking {
            val remote = mockk<FlickrApi>()
            val response = mockk<FlickrSearchResultsWrapper>()
            val expected = listOf(
                FlickrPhoto("01", "", "sec", "serv", ""),
                FlickrPhoto("01", "", "sec", "serv", ""),
                FlickrPhoto("01", "", "sec", "serv", "")
            )

            val local = mockk<ImagesSaveFlickrRepository>()

            coEvery { local.getAllImage() } returns expected
            every { response.photos.photo } returns expected
            coEvery { remote.search(text = "flowers") } returns response

            val actual = ImagesSearchFlickrRepository(remote, local)
                .search("flowers").last()

            Assert.assertEquals(
                expected.map {
                    it.toImageModel()
                }, actual
            )
        }
}