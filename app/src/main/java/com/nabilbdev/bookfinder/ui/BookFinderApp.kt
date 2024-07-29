package com.nabilbdev.bookfinder.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.nabilbdev.bookfinder.navigation.MyNavHost
import com.nabilbdev.bookfinder.ui.screens.BookFinderViewModel
import com.nabilbdev.bookfinder.ui.screens.search.SearchViewModel

@Composable
fun BookFinderApp() {

    val bookFinderViewModel: BookFinderViewModel = viewModel(
        factory = BookFinderViewModel.Factory
    )
    val searchViewModel: SearchViewModel = viewModel()
    val bookItemsList = bookFinderViewModel.bookFlow.collectAsLazyPagingItems()

    MyNavHost(bookFinderViewModel, searchViewModel, bookItemsList)
}