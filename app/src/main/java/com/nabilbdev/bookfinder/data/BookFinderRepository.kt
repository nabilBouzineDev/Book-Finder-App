package com.nabilbdev.bookfinder.data

import com.nabilbdev.bookfinder.model.BookResponse
import com.nabilbdev.bookfinder.model.Item
import com.nabilbdev.bookfinder.network.BookFinderApiService

interface BookFinderRepository {
    /** Fetches a list of volumes or a specific volume from [BookFinderApiService] */
    suspend fun getAllVolumes(
        query: String,
        maxResult: String,
    ): BookResponse

    suspend fun getSpecificVolumeInfo(
        id: String,
    ): Item
}


class NetworkBookFinderRepository(
    private val bookFinderApiService: BookFinderApiService
) : BookFinderRepository {
    override suspend fun getAllVolumes(query: String, maxResult: String): BookResponse {
        return bookFinderApiService.getAllVolumes(query, maxResult)
    }

    override suspend fun getSpecificVolumeInfo(id: String): Item {
        return bookFinderApiService.getSpecificVolumeInfo(id)
    }

}


