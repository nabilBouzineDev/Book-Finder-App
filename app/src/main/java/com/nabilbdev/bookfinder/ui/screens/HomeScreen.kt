package com.nabilbdev.bookfinder.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.nabilbdev.bookfinder.data.remote.model.Item
import com.nabilbdev.bookfinder.ui.screens.animations.ErrorComponent
import com.nabilbdev.bookfinder.ui.screens.animations.LoadingComponent
import com.nabilbdev.bookfinder.ui.screens.home.BookCollectionScreen
import com.nabilbdev.bookfinder.ui.screens.search.MySearchBar
import kotlinx.serialization.SerializationException
import okio.IOException
import retrofit2.HttpException

@Composable
fun HomeScreen(
    bookItemsList: LazyPagingItems<Item>,
    onBookCardClick: (String) -> Unit,
    onSearchClick: (String) -> Unit
) {
    when (bookItemsList.loadState.refresh) {
        is LoadState.NotLoading -> {
            Column {
                MySearchBar(onSearchClick = onSearchClick)
                ManyBookSuccessScreen(
                    onBookCardClick = onBookCardClick,
                    bookItemsList = bookItemsList
                )
            }
        }

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
    onBookCardClick: (String) -> Unit,
    bookItemsList: LazyPagingItems<Item>,
) {
    val numberOfBooks = bookItemsList.itemCount

    BookCollectionScreen(
        onBookCardClick = onBookCardClick,
        numberOfBooks = numberOfBooks,
        bookItems = bookItemsList
    )
}