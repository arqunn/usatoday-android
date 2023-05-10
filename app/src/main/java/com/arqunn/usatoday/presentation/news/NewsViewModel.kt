package com.arqunn.usatoday.presentation.news

import androidx.lifecycle.ViewModel
import com.arqunn.usatoday.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class NewsViewModel(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
}