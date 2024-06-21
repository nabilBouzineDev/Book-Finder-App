package com.nabilbdev.bookfinder.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.nabilbdev.bookfinder.BookFinderApplication
import com.nabilbdev.bookfinder.data.BookFinderRepository
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

class BookFinderViewModel(private val bookFinderRepository: BookFinderRepository) : ViewModel() {

    var isShowHomeScreen: Boolean by mutableStateOf(false)
        private set

    var bookFinderUiState: BookFinderUiState by mutableStateOf(BookFinderUiState.Loading)
        private set


    fun showHomeScreen() {
        isShowHomeScreen = true
    }

    fun getBooksInfoByQuery(query: String) {
        viewModelScope.launch {
            bookFinderUiState = BookFinderUiState.Loading
            bookFinderUiState = try {
                val bookListSize = bookFinderRepository.getAllVolumes(
                    query = query,
                    maxResult = "10"
                )
                BookFinderUiState.Success(
                    "About ${bookListSize.totalItems} Books..."
                )
            } catch (e: IOException) {
                BookFinderUiState.Error(
                    message = "Oops! Please, check your network!"
                )
            } catch (e: HttpException) {
                BookFinderUiState.Error(
                    message = "Oops! Something went wrong!"
                )
            } catch (e: SerializationException) {
                BookFinderUiState.Error(
                    message = "Oops! Something is messing!"
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookFinderApplication)
                val bookFinderRepository = application.container.bookFinderRepository
                BookFinderViewModel(bookFinderRepository = bookFinderRepository)
            }
        }
    }
}