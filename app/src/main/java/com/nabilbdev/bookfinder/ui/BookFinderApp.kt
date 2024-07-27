package com.nabilbdev.bookfinder.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.nabilbdev.bookfinder.R
import com.nabilbdev.bookfinder.navigation.MyNavHost
import com.nabilbdev.bookfinder.ui.screens.BookFinderViewModel

@Composable
fun BookFinderApp(
    modifier: Modifier = Modifier
) {

    val bookFinderViewModel: BookFinderViewModel = viewModel(
        factory = BookFinderViewModel.Factory
    )
    val bookItemsList = bookFinderViewModel.bookFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = { BookTopAppBar(bookFinderViewModel) }
    ) { innerPadding ->
        MyNavHost(bookFinderViewModel, bookItemsList, modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTopAppBar(
    bookFinderViewModel: BookFinderViewModel,
    modifier: Modifier = Modifier
) {
    if (!bookFinderViewModel.isDetailShownScreen.value) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayMedium
                )
            },
            modifier = modifier.fillMaxWidth()
        )
    } else {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Book Details",
                    style = MaterialTheme.typography.displaySmall
                )
            },
            modifier = modifier.fillMaxWidth()
        )
    }

}