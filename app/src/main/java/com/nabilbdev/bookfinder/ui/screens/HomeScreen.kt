package com.nabilbdev.bookfinder.ui.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.nabilbdev.bookfinder.data.remote.model.Item
import com.nabilbdev.bookfinder.ui.screens.animations.ErrorComponent
import com.nabilbdev.bookfinder.ui.screens.animations.LoadingComponent
import com.nabilbdev.bookfinder.ui.screens.home.BookCollectionScreen
import com.nabilbdev.bookfinder.ui.screens.search.MySearchBar
import com.nabilbdev.bookfinder.ui.screens.search.SearchViewModel
import kotlinx.serialization.SerializationException
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    searchViewModel: SearchViewModel,
    bookItemsList: LazyPagingItems<Item>,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBookCardClick: (String) -> Unit,
    onSearchClick: (String) -> Unit
) {
    when (bookItemsList.loadState.refresh) {
        is LoadState.NotLoading -> {
            Column {
                MySearchBar(
                    searchViewModel = searchViewModel,
                    onSearchClick = onSearchClick
                )
                ManyBookSuccessScreen(
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope,
                    bookItemsList = bookItemsList,
                    onBookCardClick = onBookCardClick
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


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ManyBookSuccessScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    bookItemsList: LazyPagingItems<Item>,
    onBookCardClick: (String) -> Unit,
) {
    val numberOfBooks = bookItemsList.itemCount

    BookCollectionScreen(
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedVisibilityScope,
        bookItems = bookItemsList,
        numberOfBooks = numberOfBooks,
        onBookCardClick = onBookCardClick
    )
}