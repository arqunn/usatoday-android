package com.arqunn.usatoday.presentation.search

import com.arqunn.usatoday.domain.model.NewsResponse

sealed class SearchViewState {

    object Loading : SearchViewState()

    data class Success(val data: NewsResponse) : SearchViewState()

    data class Error(val error: Exception) : SearchViewState()
}