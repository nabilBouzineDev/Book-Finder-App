package com.nabilbdev.bookfinder.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nabilbdev.bookfinder.ui.screens.animations.ErrorComponent
import com.nabilbdev.bookfinder.ui.screens.animations.LoadingComponent
import com.nabilbdev.bookfinder.ui.theme.BookFinderTheme

@Composable
fun HomeScreen(
    bookFinderUiState: BookFinderUiState,
    modifier: Modifier = Modifier
) {
    when (bookFinderUiState) {
        is BookFinderUiState.Success -> {
            SuccessScreen(
                response = bookFinderUiState.response,
                modifier = modifier
            )
        }

        is BookFinderUiState.Loading -> {
            Loading()
        }

        is BookFinderUiState.Error -> {
            ErrorScreen(
                message = bookFinderUiState.message
            )
        }
    }
}

@Composable
fun Loading() {
    LoadingComponent()
}

@Composable
fun SuccessScreen(
    response: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = response,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun ErrorScreen(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ErrorComponent()
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ResultPreview() {
    BookFinderTheme {
        SuccessScreen(response = "Testing Ui")
    }
}
