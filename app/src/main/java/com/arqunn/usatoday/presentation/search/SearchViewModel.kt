package com.arqunn.usatoday.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arqunn.usatoday.data.remote.util.ApiResult
import com.arqunn.usatoday.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private var searchJob: Job? = null

    private val _viewState = MutableStateFlow<SearchViewState>(SearchViewState.Loading)
    val viewState = _viewState.asStateFlow()

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