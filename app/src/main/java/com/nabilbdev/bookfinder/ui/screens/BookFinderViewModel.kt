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
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nabilbdev.bookfinder.BookFinderApplication
import com.nabilbdev.bookfinder.data.remote.model.Item
import com.nabilbdev.bookfinder.data.remote.pagination.BooksPagingSource
import com.nabilbdev.bookfinder.data.repository.BookFinderRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import okio.IOException
import retrofit2.HttpException

sealed interface BookFinderUiState {

    data class Success(
        val response: Item
    ) : BookFinderUiState

    data object Loading : BookFinderUiState
    data class Error(
        val message: String
    ) : BookFinderUiState
}

class BookFinderViewModel(private val bookFinderRepository: BookFinderRepository) : ViewModel() {


    var bookFinderUiState: BookFinderUiState by mutableStateOf(BookFinderUiState.Loading)
        private set

    private val _userInputQuery = MutableStateFlow("")

    fun takeUserQuery(query: String) {
        _userInputQuery.value = query
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val bookFlow: Flow<PagingData<Item>> = _userInputQuery.flatMapLatest { query ->
        Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {
                BooksPagingSource(bookFinderRepository, query)
            }
        ).flow
            .cachedIn(viewModelScope)
    }

    fun getSingleBook(bookId: String) {
        viewModelScope.launch {
            bookFinderUiState = BookFinderUiState.Loading
            try {
                val book = bookFinderRepository.getSpecificVolumeInfo(bookId)
                bookFinderUiState = BookFinderUiState.Success(book)
            } catch (e: IOException) {
                BookFinderUiState.Error(
                    message = "Oops! Please, check your network!"
                )
            } catch (e: HttpException) {
                BookFinderUiState.Error(
                    message = "Oops! Something went wrong with the server!"
                )
            } catch (e: SerializationException) {
                BookFinderUiState.Error(
                    message = "Oops! Something messing with the data!"
                )
            } catch (e: Exception) {
                BookFinderUiState.Error(
                    message = "An unexpected error occurred."
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