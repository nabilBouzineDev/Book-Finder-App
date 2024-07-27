package com.nabilbdev.bookfinder.ui.screens.detail

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import com.nabilbdev.bookfinder.data.remote.model.Item
import com.nabilbdev.bookfinder.ui.screens.BookFinderUiState
import com.nabilbdev.bookfinder.ui.screens.animations.ErrorComponent

@Composable
fun DetailScreen(
    bookFinderUiState: BookFinderUiState,
    onNavigateUp: () -> Unit
) {
    when (bookFinderUiState) {
        is BookFinderUiState.Success -> {
            SingleBookSuccessScreen(
                response = bookFinderUiState.response
            )
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

@Composable
fun SingleBookSuccessScreen(
    response: Item
) {
    val book = response.volumeInfo

    BookInfoScreen(
        image = book.imageLinks.thumbnail,
        title = book.title,
        authors = book.authors,
        category = book.categories[0].split(" / ")[0],
        description = book.description,
        previewLink = book.previewLink,
        publishedDate = book.publishedDate,
    )
}
