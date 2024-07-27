package com.nabilbdev.bookfinder.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nabilbdev.bookfinder.ui.screens.animations.SearchingComponent
import com.nabilbdev.bookfinder.ui.theme.card
import com.nabilbdev.bookfinder.ui.theme.darkCard


@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    onSearchClick: (String) -> Unit
) {
    Column {
        MySearchBar(searchViewModel = searchViewModel, onSearchClick = onSearchClick)
        SearchingComponent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    searchViewModel: SearchViewModel,
    modifier: Modifier = Modifier,
    onSearchClick: (String) -> Unit = {}
) {
    // query given by the user
    var text by remember { mutableStateOf("") }

    // Indicates if the user clicks on search bar component
    var active by remember {
        mutableStateOf(false)
    }

    SearchBar(
        modifier = modifier
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

                // Add it to the search history
                searchViewModel.addSearchQuery(query)

                // fetch data to the home screen
                onSearchClick(query)
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
        searchViewModel.searchHistory.forEach { history ->
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
        if (searchViewModel.searchHistory.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .padding(all = 14.dp)
                    .fillMaxWidth()
                    .clickable {
                        searchViewModel.clearHistory()
                    },
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                text = "Clear all history"
            )
        }
    }
}