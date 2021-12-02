package io.github.diduseetheocean.stockimageapp.data.repository.pexels

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

class ImagesSearchPexelsRepositoryTest {

    @Test
    fun `Fetching results from Remote THEN caching results THEN comparing cache with results`() =
        runBlocking {
            val remote = mockk<PexelsApi>()
            val response = mockk<PexelsSearchResponse>()
            val expected = listOf(PexelsImage(), PexelsImage(), PexelsImage())

            val local = mockk<ImagesSavePexelsRepository>()

            coEvery { local.getAllImage() } returns expected
            every { response.photos } returns expected
            coEvery { remote.search("flowers") } returns response

            val actual = ImagesSearchPexelsRepository(remote, local)
                .search("flowers").last()

            Assert.assertEquals(
                expected.map {
                    it.toImageModel()
                }, actual
            )
        }
}