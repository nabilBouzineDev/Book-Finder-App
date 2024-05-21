package com.nabilbdev.bookfinder.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabilbdev.bookfinder.network.BookFinderApi
import kotlinx.coroutines.launch
import okio.IOException

sealed interface BookFinderUiState {
    data class Success(val bookInfo: String) : BookFinderUiState
    object Loading : BookFinderUiState
    object Error : BookFinderUiState
}

class BookFinderViewModel() : ViewModel() {
    var bookFinderUiState: BookFinderUiState by mutableStateOf(BookFinderUiState.Loading)
        private set

    init {
        getBookInfo()
    }

    fun getBookInfo() {
        viewModelScope.launch {
            bookFinderUiState = BookFinderUiState.Loading
            bookFinderUiState = try {
                val book =
                    BookFinderApi.retrofitService.getAllVolumes(q = "programming", maxResult = "10")
                BookFinderUiState.Success(
                    "Success: ${book.totalItems} books are fetched!"
                )
            } catch (e: IOException) {
                BookFinderUiState.Error
            }
        }
    }
}