package io.github.diduseetheocean.stockimageapp.data.api.pexels

import io.github.diduseetheocean.stockimageapp.TestUtils
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException

class PexelsApiTest {

    @get:Rule
    private val mockWebServer = MockWebServer()

    private val api by lazy {
        pexelsApi(
            mockWebServer.url("/"),
            client = {
                OkHttpClient.Builder().build()
            })
    }

    @Test
    fun `Fetching should succeed`() = runBlocking {
        mockWebServer.enqueue(
            response = MockResponse()
                .setBody(TestUtils.loadJsonFile("PexelsSearchResponse.json"))
                .setResponseCode(200)
        )
        val search = api.search("fruits")

        Assert.assertTrue(search.photos?.size == 20)
    }

    @Test
    fun `Fetching should fail`(): Unit = runBlocking {
        mockWebServer.enqueue(
            response = MockResponse()
                .setBody(TestUtils.loadJsonFile("PexelsSearchResponse.json"))
                .setResponseCode(400)
        )

        Assert.assertThrows(HttpException::class.java) {
            runBlocking {
                api.search("fruits")
            }
        }.also {
            Assert.assertEquals(400, it.code())
        }
    }
}