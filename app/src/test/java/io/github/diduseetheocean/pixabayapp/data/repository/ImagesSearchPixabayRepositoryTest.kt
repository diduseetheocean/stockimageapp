package io.github.diduseetheocean.pixabayapp.data.repository

import io.github.diduseetheocean.pixabayapp.data.api.pixabay.PixabayApi
import io.github.diduseetheocean.pixabayapp.data.api.pixabay.PixabayImage
import io.github.diduseetheocean.pixabayapp.data.api.pixabay.PixabaySearchResponse
import io.github.diduseetheocean.pixabayapp.data.api.pixabay.toImageModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ImagesSearchPixabayRepositoryTest {

    @Test
    fun `Fetching results from Remote THEN caching results THEN comparing cache with results`() =
        runBlocking {
            val remote = mockk<PixabayApi>()
            val response = mockk<PixabaySearchResponse>()
            val expected = listOf(PixabayImage(), PixabayImage(), PixabayImage())

            val local = mockk<ImagesSavePixabayRepository>()

            coEvery { local.getAllImage() } returns expected
            every { response.images } returns expected
            coEvery { remote.search("flowers") } returns response

            val actual = ImagesSearchPixabayRepository(remote, local)
                .search("flowers").last()

            Assert.assertEquals(
                expected.map {
                    it.toImageModel()
                }, actual
            )
        }
}