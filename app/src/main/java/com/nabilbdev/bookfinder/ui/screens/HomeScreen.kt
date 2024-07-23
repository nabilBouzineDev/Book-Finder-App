package com.nabilbdev.bookfinder.ui.screens

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.nabilbdev.bookfinder.data.remote.model.BookResponse
import com.nabilbdev.bookfinder.data.remote.model.Item
import com.nabilbdev.bookfinder.ui.screens.animations.ErrorComponent
import com.nabilbdev.bookfinder.ui.screens.animations.LoadingComponent
import com.nabilbdev.bookfinder.ui.screens.multiplebooks.BookCollectionScreen
import com.nabilbdev.bookfinder.ui.screens.singlebook.BookInfoScreen
import kotlinx.serialization.SerializationException
import okio.IOException
import retrofit2.HttpException

@Composable
fun HomeScreen(
    bookItemsList: LazyPagingItems<Item>,
) {
    when (bookItemsList.loadState.refresh) {
        is LoadState.NotLoading -> ManyBookSuccessScreen(bookItemsList = bookItemsList)
        is LoadState.Loading -> LoadingComponent()
        is LoadState.Error -> {
            val errorMessage = when ((bookItemsList.loadState.refresh as LoadState.Error).error) {
                is IOException -> "Oops! Please, check your network!"
                is HttpException -> "Oops! Something went wrong with the server!"
                is SerializationException -> "Oops! Something messing with the data!"
                else -> "An unexpected error occurred."
            }
            ErrorComponent(errorMessage) // Pass the error message to your ErrorComponent
        }
    }
}


@Composable
fun ManyBookSuccessScreen(
    bookItemsList: LazyPagingItems<Item>,
) {
    val numberOfBooks = bookItemsList.itemCount

    BookCollectionScreen(
        numberOfBooks = numberOfBooks,
        bookItems = bookItemsList
    )
}

@Composable
fun OneBookSuccessScreen(
    response: BookResponse,
) {
    val book = response.items[0].volumeInfo
    BookInfoScreen(
        image = book.imageLinks.thumbnail,
        title = book.title,
        authors = book.authors,
        category = book.categories[0],
        description = book.description,
        previewLink = book.previewLink,
        publishedDate = book.publishedDate,
    )
}
