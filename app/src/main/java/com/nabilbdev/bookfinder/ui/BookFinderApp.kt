package com.nabilbdev.bookfinder.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nabilbdev.bookfinder.R
import com.nabilbdev.bookfinder.ui.screens.BookFinderViewModel
import com.nabilbdev.bookfinder.ui.screens.HomeScreen

@Composable
fun BookFinderApp(
    bookFinderViewModel: BookFinderViewModel
) {
    Scaffold(
        topBar = {
            BookTopAppBar()
        }
    ) {
        HomeScreen(
            bookFinderUiState = bookFinderViewModel.bookFinderUiState,
            modifier = Modifier.padding(it)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayMedium
            )
        },
        modifier = modifier
    )
}