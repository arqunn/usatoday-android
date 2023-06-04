package com.arqunn.usatoday.presentation.newsdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<NewsDetailViewEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun checkIfFavorite(article: Article) = viewModelScope.launch {
        newsRepository.getFavoriteArticleById(article.uuid).let { article ->
            _eventFlow.emit(
                NewsDetailViewEvent.ShowFavoriteButton(
                    isMyFavorite = article != null
                )
            )
        }
    }

    fun addRemoveFavorite(
        article: Article,
        isMyFavorite: Boolean
    ) = viewModelScope.launch(Dispatchers.IO) {
        if (isMyFavorite) {
            newsRepository.addToFavorites(article)
            _eventFlow.emit(NewsDetailViewEvent.ShowAddedToFavoritesDialog)
        } else {
            newsRepository.removeFromFavorites(article)
        }
    }
}