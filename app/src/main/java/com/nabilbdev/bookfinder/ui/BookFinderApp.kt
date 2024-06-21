package com.nabilbdev.bookfinder.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nabilbdev.bookfinder.R
import com.nabilbdev.bookfinder.ui.screens.BookFinderViewModel
import com.nabilbdev.bookfinder.ui.screens.HomeScreen
import com.nabilbdev.bookfinder.ui.screens.animations.SearchingComponent
import com.nabilbdev.bookfinder.ui.theme.card
import com.nabilbdev.bookfinder.ui.theme.darkCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookFinderApp(
    modifier: Modifier = Modifier
) {

    // query given by the user
    var text by remember { mutableStateOf("") }

    // Indicates if the user clicks on search bar component
    var active by remember {
        mutableStateOf(false)
    }

    // list of history search(usually fetched from db)
    val searchHistory = remember {
        mutableStateListOf<String>()
    }

    Scaffold(
        topBar = {
            BookTopAppBar()
        }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background,
        ) {
            val bookFinderViewModel: BookFinderViewModel = viewModel(
                factory = BookFinderViewModel.Factory
            )
            Column {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    enabled = true,
                    query = text,
                    onQueryChange = { newWord ->
                        text = newWord
                    },
                    onSearch = { query ->
                        // When user clicks search button
                        if (query.isNotBlank()) {
                            // avoid duplicating history
                            if (!searchHistory.contains(query))
                                searchHistory.add(0, query)

                            bookFinderViewModel.getBooksInfoByQuery(query)
                            bookFinderViewModel.showHomeScreen()
                            active = false
                        }
                    },
                    active = active,
                    onActiveChange = { inactive ->
                        active = inactive
                    },
                    placeholder = {
                        Text(
                            text = "Search for books..."
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search"
                        )
                    },
                    trailingIcon = {
                        if (active) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = "Clear your search",
                                modifier = Modifier.clickable {
                                    // To clear the user's typing search
                                    text = ""
                                    active = false
                                }
                            )
                        }
                    },
                    colors = SearchBarDefaults.colors(
                        containerColor = if (isSystemInDarkTheme()) darkCard else card,
                        inputFieldColors = TextFieldDefaults.colors(
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.surface
                        )
                    )
                ) {
                    searchHistory.forEach { history ->
                        Row(
                            modifier = Modifier
                                .padding(14.dp)
                                .clickable {
                                    text = history
                                }
                        ) {
                            Icon(
                                modifier = Modifier.padding(end = 10.dp),
                                imageVector = Icons.Default.History,
                                contentDescription = "History icon"
                            )
                            Text(text = history)
                        }
                        HorizontalDivider()
                    }
                    if (searchHistory.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .padding(all = 14.dp)
                                .fillMaxWidth()
                                .clickable {
                                    searchHistory.clear()
                                },
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall,
                            text = "Clear all history"
                        )
                    }
                }
            }
            if (!active) {
                when {
                    bookFinderViewModel.isShowHomeScreen ->
                        HomeScreen(bookFinderUiState = bookFinderViewModel.bookFinderUiState)

                    else -> SearchingComponent()
                }
            }
        }
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