package io.github.diduseetheocean.stockimageapp.data.api.pixabay

import io.github.diduseetheocean.stockimageapp.TestUtils
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class PixabayApiTest {

    @get:Rule
    private val mockWebServer = MockWebServer()

    private val api by lazy {
        pixabayApi(mockWebServer.url("/"), client = {
            OkHttpClient.Builder().build()
        })
    }

    @Test
    fun `Fetching should succeed`() = runBlocking {
        mockWebServer.enqueue(
            response = MockResponse()
                .setBody(TestUtils.loadJsonFile("PixabaySearchResponse.json"))
                .setResponseCode(200)
        )
        val search = api.search("flowers")

        Assert.assertTrue(search.images?.size == 20)
    }

    @Test
    fun `Fetching should fail`(): Unit = runBlocking {
        mockWebServer.enqueue(
            response = MockResponse()
                .setBody(TestUtils.loadJsonFile("PixabaySearchResponse.json"))
                .setResponseCode(400)
        )

        Assert.assertThrows(retrofit2.HttpException::class.java) {
            runBlocking {
                api.search("flowers")
            }
        }.also {
            Assert.assertEquals(400, it.code())
        }
    }
}