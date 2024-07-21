package com.nabilbdev.bookfinder.ui.screens

import androidx.compose.runtime.Composable
import com.nabilbdev.bookfinder.model.BookResponse
import com.nabilbdev.bookfinder.ui.screens.animations.ErrorComponent
import com.nabilbdev.bookfinder.ui.screens.animations.LoadingComponent
import com.nabilbdev.bookfinder.ui.screens.multiplebooks.BookCollectionScreen
import com.nabilbdev.bookfinder.ui.screens.singlebook.BookInfoScreen

@Composable
fun HomeScreen(
    bookFinderUiState: BookFinderUiState,
) {
    when (bookFinderUiState) {
        is BookFinderUiState.Success -> {
            ManyBookSuccessScreen(
                response = bookFinderUiState.response,
            )
        }

        is BookFinderUiState.Loading -> {
            LoadingComponent()
        }

        is BookFinderUiState.Error -> {
            ErrorComponent(message = bookFinderUiState.message)
        }
    }
}


@Composable
fun ManyBookSuccessScreen(
    response: BookResponse
) {
    val numberOfBooks = response.totalItems

    BookCollectionScreen(
        numberOfBooks = numberOfBooks,
        bookItems = response
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
