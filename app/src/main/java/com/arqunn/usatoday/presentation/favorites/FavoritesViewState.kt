package com.arqunn.usatoday.presentation.favorites

import com.arqunn.usatoday.domain.model.Article

data class FavoritesViewState(
    val favorites: List<Article> = emptyList()
)