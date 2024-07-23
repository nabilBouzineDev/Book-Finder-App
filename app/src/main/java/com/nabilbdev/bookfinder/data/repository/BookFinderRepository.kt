package com.nabilbdev.bookfinder.data.repository

import com.nabilbdev.bookfinder.data.remote.model.BookResponse
import com.nabilbdev.bookfinder.data.remote.model.Item
import com.nabilbdev.bookfinder.data.remote.network.BookFinderApiService

interface BookFinderRepository {
    /** Fetches a list of volumes or a specific volume from [BookFinderApiService] */
    suspend fun getAllVolumes(query: String, startIndex: Int): BookResponse

    suspend fun getSpecificVolumeInfo(
        id: String,
    ): Item
}


class NetworkBookFinderRepository(
    private val bookFinderApiService: BookFinderApiService
) : BookFinderRepository {
    override suspend fun getAllVolumes(query: String, startIndex: Int): BookResponse {
        return bookFinderApiService.getAllVolumes(query, startIndex)
    }

    override suspend fun getSpecificVolumeInfo(id: String): Item {
        return bookFinderApiService.getSpecificVolumeInfo(id)
    }

}


