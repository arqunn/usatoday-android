package com.arqunn.usatoday.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arqunn.usatoday.data.remote.util.ApiResult
import com.arqunn.usatoday.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow<NewsViewState>(NewsViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        getAllNews()
    }

    private fun getAllNews() {
        newsRepository.getAllNews().onEach { apiResult ->
            when (apiResult) {
                is ApiResult.Error -> {
                    _viewState.emit(NewsViewState.Error(apiResult.error))
                }
                is ApiResult.Success -> {
                    _viewState.emit(NewsViewState.Success(apiResult.data))
                }
                is ApiResult.Loading -> {
                    _viewState.emit(NewsViewState.Loading)
                }
            }
        }.launchIn(viewModelScope)
    }
}