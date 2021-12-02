package io.github.diduseetheocean.stockimageapp.data.api.pexels

import io.github.diduseetheocean.stockimageapp.BuildConfig
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApi {
    @GET(".")
    suspend fun search(
        @Query("query") query: String,
        @Query("per_page") perPage: Int = resultsPerPage
    ): PexelsSearchResponse
}

const val apiUrl = "https://api.pexels.com/v1/search/"
const val resultsPerPage = 20


fun pexelsApi(
    baseUrl: HttpUrl = apiUrl.toHttpUrl(),
    client: () -> OkHttpClient = { makeOkHttpClient() },
): PexelsApi {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client())
        .build()

    return retrofit.create(PexelsApi::class.java)
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
    val headers: Headers = it.request().headers
        .newBuilder()
        .add("Authorization", BuildConfig.api_key_pexels)
        .build()
    val request: Request = it.request().newBuilder().headers(headers).build()
    it.proceed(request)
}

private fun loggingInterceptor(): Interceptor =
    HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }