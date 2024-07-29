package com.nabilbdev.bookfinder.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import com.nabilbdev.bookfinder.data.remote.model.Item
import com.nabilbdev.bookfinder.ui.screens.BookFinderViewModel
import com.nabilbdev.bookfinder.ui.screens.HomeScreen
import com.nabilbdev.bookfinder.ui.screens.detail.DetailScreen
import com.nabilbdev.bookfinder.ui.screens.search.SearchScreen
import com.nabilbdev.bookfinder.ui.screens.search.SearchViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MyNavHost(
    bookFinderViewModel: BookFinderViewModel,
    searchViewModel: SearchViewModel,
    bookItemsList: LazyPagingItems<Item>,
    navController: NavHostController = rememberNavController()
) {
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = Search) {
            composable<Search> {
                SearchScreen(searchViewModel = searchViewModel) { query ->
                    bookFinderViewModel.takeUserQuery(query)
                    navController.navigate(Home)
                }
            }
            composable<Home> {
                HomeScreen(
                    searchViewModel = searchViewModel,
                    bookItemsList = bookItemsList,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                    onSearchClick = { query ->
                        bookFinderViewModel.takeUserQuery(query)
                    },
                    onBookCardClick = { id ->
                        bookFinderViewModel.getSingleBook(bookId = id)
                        navController.navigate(Detail)
                    }
                )
            }
            composable<Detail> {
                DetailScreen(
                    bookFinderUiState = bookFinderViewModel.bookFinderUiState,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                    onNavigateUp = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}