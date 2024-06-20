package com.nabilbdev.bookfinder.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nabilbdev.bookfinder.BuildConfig
import com.nabilbdev.bookfinder.model.BookResponse
import com.nabilbdev.bookfinder.model.Item
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL =
    "https://www.googleapis.com/books/v1/"


private val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface BookFinderApiService {
    @GET("volumes")
    suspend fun getAllVolumes(
        @Query("q") q: String,
        @Query("maxResult") maxResult: String,
        @Query("key") key: String = BuildConfig.API_KEY
    ): BookResponse

    @GET("volumes/{id}")
    suspend fun getSpecificVolumeInfo(
        @Path("id") id: String,
        @Query("key") key: String = BuildConfig.API_KEY
    ): Item
}

object BookFinderApi {
    val retrofitService: BookFinderApiService by lazy {
        retrofit.create(BookFinderApiService::class.java)
    }
}
