package com.arqunn.usatoday.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arqunn.usatoday.data.remote.util.ApiResult
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.domain.repository.NewsRepository
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

    fun addRemoveFavorite(
        article: Article,
        isMyFavorite: Boolean
    ) = viewModelScope.launch(Dispatchers.IO) {
        if (isMyFavorite) {
            newsRepository.addToFavorites(article)
            _eventFlow.emit(NewsViewEvent.ShowAddedToFavoritesDialog)
        } else {
            newsRepository.removeFromFavorites(article)
        }
    }
}