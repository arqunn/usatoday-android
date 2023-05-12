package com.arqunn.usatoday.presentation.news

import com.arqunn.usatoday.domain.model.NewsResponse

sealed class NewsViewState {
    object Loading: NewsViewState()
    data class Success(val data: NewsResponse): NewsViewState()
    data class Error(val error: Exception): NewsViewState()
}