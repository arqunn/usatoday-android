package com.arqunn.usatoday.presentation.newsdetail

sealed class NewsDetailViewEvent {
    object ShowAddedToFavoritesDialog: NewsDetailViewEvent()
}