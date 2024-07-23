package com.nabilbdev.bookfinder.data.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nabilbdev.bookfinder.data.remote.network.BookFinderApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val bookFinderRepository: BookFinderRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl =
        "https://www.googleapis.com/books/v1/"


    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: BookFinderApiService by lazy {
        retrofit.create(BookFinderApiService::class.java)
    }

    override val bookFinderRepository: BookFinderRepository by lazy {
        NetworkBookFinderRepository(retrofitService)
    }
}