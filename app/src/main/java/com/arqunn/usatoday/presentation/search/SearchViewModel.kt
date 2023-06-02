package com.arqunn.usatoday.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arqunn.usatoday.data.remote.util.ApiResult
import com.arqunn.usatoday.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private var searchJob: Job? = null

    private val _viewState = MutableSharedFlow<SearchViewState>()
    val viewState = _viewState.asSharedFlow()

    fun searchForNews(query: String) {
        searchJob?.cancel()
        searchJob = newsRepository.searchForNews(query).onEach { apiResult ->
            delay(1000)
            when (apiResult) {
                is ApiResult.Error -> {
                    _viewState.emit(SearchViewState.Error(apiResult.error))
                }
                is ApiResult.Success -> {
                    _viewState.emit(SearchViewState.Success(apiResult.data))
                }
                is ApiResult.Loading -> {
                    _viewState.emit(SearchViewState.Loading)
                }
            }
        }.launchIn(viewModelScope)
    }
}