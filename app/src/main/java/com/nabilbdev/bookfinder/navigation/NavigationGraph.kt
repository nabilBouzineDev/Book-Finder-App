package com.nabilbdev.bookfinder.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@Composable
fun MyNavHost(
    bookFinderViewModel: BookFinderViewModel,
    bookItemsList: LazyPagingItems<Item>,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Search, modifier = modifier) {
        composable<Search> {
            SearchScreen { query ->
                bookFinderViewModel.takeUserQuery(query)
                navController.navigate(Home)
            }
        }
        composable<Home> {
            HomeScreen(
                bookItemsList = bookItemsList,
                onSearchClick = { query ->
                    bookFinderViewModel.takeUserQuery(query)
                },
                onBookCardClick = { id ->
                    bookFinderViewModel.getSingleBook(bookId = id)
                    navController.navigate(Detail)
                    bookFinderViewModel.isDetailShownScreen.value = true
                }
            )
        }
        composable<Detail> {
            DetailScreen(
                bookFinderUiState = bookFinderViewModel.bookFinderUiState,
                onNavigateUp = {
                    navController.navigateUp()
                    bookFinderViewModel.isDetailShownScreen.value = false
                }
            )
        }
    }
}