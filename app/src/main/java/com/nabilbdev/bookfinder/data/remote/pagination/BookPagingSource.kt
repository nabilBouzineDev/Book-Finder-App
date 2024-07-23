package com.nabilbdev.bookfinder.data.remote.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nabilbdev.bookfinder.data.remote.model.Item
import com.nabilbdev.bookfinder.data.repository.BookFinderRepository
import okio.IOException
import retrofit2.HttpException

class BooksPagingSource(private val repository: BookFinderRepository, private val query: String) :
    PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        try {
            val pageNumber = params.key ?: 0 // Start with page 0
            val response = repository.getAllVolumes(
                query = query,
                startIndex = pageNumber * 10
            )
            return LoadResult.Page(
                data = response.items,
                prevKey = if (pageNumber > 0) pageNumber - 1 else null,
                nextKey = if (response.items.isNotEmpty()) pageNumber + 1 else null
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }
}