package com.nabilbdev.bookfinder.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.nabilbdev.bookfinder.network.BookFinderApi
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import okio.IOException

sealed interface BookFinderUiState {

    data class Success(
        val response: String
    ) : BookFinderUiState

    data object Loading : BookFinderUiState
    data class Error(
        val message: String
    ) : BookFinderUiState
}

class BookFinderViewModel : ViewModel() {

    var isShowHomeScreen: Boolean by mutableStateOf(false)
        private set

    var bookFinderUiState: BookFinderUiState by mutableStateOf(BookFinderUiState.Loading)
        private set

    init {
        getBooksInfoByQuery(query = "")
    }

    fun showHomeScreen() {
        isShowHomeScreen = true
    }

    fun getBooksInfoByQuery(query: String) {
        if (query.isNotBlank()) {
            viewModelScope.launch {
                bookFinderUiState = BookFinderUiState.Loading
                Log.d("BookFinder", "Loading: ${bookFinderUiState::class.simpleName}")
                bookFinderUiState = try {
                    val book =
                        BookFinderApi.retrofitService.getAllVolumes(q = query, maxResult = "10")
                    BookFinderUiState.Success(
                        "About ${book.totalItems} Books..."
                    )
                } catch (e: IOException) {
                    BookFinderUiState.Error(
                        message = "Sorry! We can't find your $query! Try again later."
                    )
                } catch (e: HttpException) {
                    BookFinderUiState.Error(
                        message = "Oops! Something went wrong!"
                    )
                } catch (e: SerializationException) {
                    BookFinderUiState.Error(
                        message = "Oops! Something is messing"
                    )
                }
                Log.d("BookFinder", "Error or Success: ${bookFinderUiState::class.simpleName}")
            }
        }
    }
}