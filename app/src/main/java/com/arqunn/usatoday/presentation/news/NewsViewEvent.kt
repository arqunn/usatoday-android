package com.arqunn.usatoday.presentation.news

sealed class NewsViewEvent {
    object ShowAddedToFavoritesDialog: NewsViewEvent()
}