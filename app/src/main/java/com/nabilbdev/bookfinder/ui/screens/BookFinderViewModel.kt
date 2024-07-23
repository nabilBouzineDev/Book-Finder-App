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

class BookFinderViewModel(private val bookFinderRepository: BookFinderRepository) : ViewModel() {

    var isShowHomeScreen: Boolean by mutableStateOf(false)
        private set

    private val _userInputQuery = MutableStateFlow("")

    fun takeUserQuery(query: String) {
        _userInputQuery.value = query
    }

    fun showHomeScreen() {
        isShowHomeScreen = true
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