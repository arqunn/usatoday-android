package com.arqunn.usatoday.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(FavoritesViewState())
    val viewState = _viewState.asStateFlow()

    init {
        getAllFavorites()
    }

    private fun getAllFavorites() {
        newsRepository.getAllFavorites().onEach { articles ->
            _viewState.update {
                it.copy(favorites = articles)
            }
        }.launchIn(viewModelScope)
    }

    fun removeFromFavorites(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.removeFromFavorites(article)
        }
    }
}