package com.nabilbdev.bookfinder.ui.screens.detail

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nabilbdev.bookfinder.data.remote.model.Item
import com.nabilbdev.bookfinder.ui.components.DetailScreenTopAppBar
import com.nabilbdev.bookfinder.ui.screens.BookFinderUiState
import com.nabilbdev.bookfinder.ui.screens.animations.ErrorComponent

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    bookFinderUiState: BookFinderUiState,
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit
) {
    when (bookFinderUiState) {
        is BookFinderUiState.Success -> {
            Scaffold(
                topBar = { DetailScreenTopAppBar(onBackButtonClicked = onNavigateUp) }
            ) { innerPadding ->
                SingleBookSuccessScreen(
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope,
                    response = bookFinderUiState.response,
                    modifier = modifier.padding(innerPadding)
                )
            }
        }

        is BookFinderUiState.Loading -> {
            /** Do Nothing */
        }

        is BookFinderUiState.Error -> {
            ErrorComponent(message = bookFinderUiState.message)
        }
    }
    BackHandler(enabled = true, onBack = onNavigateUp)
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SingleBookSuccessScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    response: Item,
    modifier: Modifier = Modifier
) {
    val book = response.volumeInfo

    BookInfoScreen(
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedVisibilityScope,
        bookId = response.id,
        image = book.imageLinks.thumbnail,
        title = book.title,
        authors = book.authors,
        category = book.categories[0].split(" / ")[0],
        description = book.description,
        previewLink = book.previewLink,
        publishedDate = book.publishedDate,
        modifier = modifier
    )
}
