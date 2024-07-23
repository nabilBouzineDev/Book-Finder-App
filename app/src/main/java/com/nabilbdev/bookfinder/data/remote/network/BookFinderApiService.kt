package com.nabilbdev.bookfinder.data.remote.network

import com.nabilbdev.bookfinder.BuildConfig
import com.nabilbdev.bookfinder.data.remote.model.BookResponse
import com.nabilbdev.bookfinder.data.remote.model.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface BookFinderApiService {
    @GET("volumes")
    suspend fun getAllVolumes(
        @Query("q") q: String,
        @Query("startIndex") startIndex: Int,
        @Query("key") key: String = BuildConfig.API_KEY
    ): BookResponse

    @GET("volumes/{id}")
    suspend fun getSpecificVolumeInfo(
        @Path("id") id: String,
        @Query("key") key: String = BuildConfig.API_KEY
    ): Item
}