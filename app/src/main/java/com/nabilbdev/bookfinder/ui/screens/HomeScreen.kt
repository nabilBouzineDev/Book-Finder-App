package com.nabilbdev.bookfinder.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nabilbdev.bookfinder.ui.theme.BookFinderTheme

@Composable
fun HomeScreen(
    bookFinderUiState: BookFinderUiState,
    modifier: Modifier = Modifier
) {
    when (bookFinderUiState) {
        is BookFinderUiState.Success -> {
            Result(
                result = bookFinderUiState.bookInfo,
                modifier = modifier
            )
        }

        is BookFinderUiState.Loading -> {
            Loading()
        }

        is BookFinderUiState.Error -> {
            NoResult()
        }
    }
}

@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Still Loading",
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
fun Result(result: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = result,
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
fun NoResult() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No result found",
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ResultPreview() {
    BookFinderTheme {
        Result(result = "Testing Ui")
    }
}
