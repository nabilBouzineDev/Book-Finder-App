package com.nabilbdev.bookfinder.ui.screens.search

import androidx.compose.runtime.mutableStateListOf

class SearchViewModel : androidx.lifecycle.ViewModel() {
    private val _searchHistory = mutableStateListOf<String>()
    val searchHistory: List<String> = _searchHistory

    fun addSearchQuery(query: String) {

        // This check to avoid duplicated history
        if (!_searchHistory.contains(query)) {
            _searchHistory.add(0, query)
        }
    }

    fun clearHistory() {
        _searchHistory.clear()
    }
}