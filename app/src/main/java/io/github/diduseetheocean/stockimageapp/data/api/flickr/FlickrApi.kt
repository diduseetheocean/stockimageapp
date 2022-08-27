package io.github.diduseetheocean.stockimageapp.data.api.flickr

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.diduseetheocean.stockimageapp.BuildConfig
import io.github.diduseetheocean.stockimageapp.data.model.ImageModel
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET(".")
    suspend fun search(
        @Query("method") method: String = methodToCall,
        @Query("format") format: String = formatResponse,
        @Query("nojsoncallback") nojsoncallback: String = noFunctionWrapper,
        @Query("text") text: String,
        @Query("per_page") perPage: Int = resultsPerPage
    ): FlickrSearchResultsWrapper
}

const val apiUrl = "https://www.flickr.com/services/rest/"
const val methodToCall = "flickr.photos.search"
const val formatResponse = "json"
const val noFunctionWrapper = "1"
const val resultsPerPage = 20

fun flickrApi(
    baseUrl: HttpUrl = apiUrl.toHttpUrl(),
    client: () -> OkHttpClient = { makeOkHttpClient() },
): FlickrApi {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client())
        .build()

    return retrofit.create(FlickrApi::class.java)
}

private fun makeOkHttpClient(
    logging: () -> Interceptor = { loggingInterceptor() },
    authorization: () -> Interceptor = { authorizationInterceptor() },
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(logging())
        .addInterceptor(authorization())
        .build()

private fun authorizationInterceptor() = Interceptor {
    val url: HttpUrl = it.request().url
        .newBuilder()
        .addQueryParameter("api_key", BuildConfig.api_key_flickr)
        .build()
    val request: Request = it.request().newBuilder().url(url).build()
    it.proceed(request)
}

private fun loggingInterceptor(): Interceptor =
    HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

// TODO Create a type adapter to strip the unnecessary data
data class FlickrSearchResultsWrapper(val photos: FlickrSearchResultsPage)
data class FlickrSearchResultsPage(val page: Int, val photo: List<FlickrPhoto>)
@Entity
data class FlickrPhoto(@PrimaryKey val id: String, val owner: String, val secret: String, val server: String, val title: String) {
    val url
        get() = "https://live.staticflickr.com/{server-id}/{id}_{secret}_m.jpg"
            .replace("{server-id}", server)
            .replace("{id}", id)
            .replace("{secret}", secret)

    val largeUrl
        get() = "https://live.staticflickr.com/{server-id}/{id}_{secret}_c.jpg"
            .replace("{server-id}", server)
            .replace("{id}", id)
            .replace("{secret}", secret)
}

fun FlickrPhoto.toImageModel() = ImageModel(
    imageId = id?.toLong() ?: -1,
    userName = title,
    userImageURL = "",
    url = url ?: "",
    likes = "",
    downloads = "",
    comments = "",
    tags = emptyList(),
    largeImageURL = largeUrl
)