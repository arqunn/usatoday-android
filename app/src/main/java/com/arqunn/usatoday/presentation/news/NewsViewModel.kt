package com.arqunn.usatoday.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arqunn.usatoday.data.remote.util.ApiResult
import com.arqunn.usatoday.domain.repository.NewsRepository
import com.arqunn.usatoday.util.extensions.toInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow<NewsViewState>(NewsViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<NewsViewEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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

    fun addUpdateFavorites(
        articleId: Int,
        isMyFavorite: Boolean
    ) = viewModelScope.launch(Dispatchers.IO) {
        newsRepository.addUpdateFavorites(articleId, isMyFavorite.toInt())
        if (isMyFavorite) {
            _eventFlow.emit(NewsViewEvent.ShowAddedToFavoritesDialog)
        }
    }
}