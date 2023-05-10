package com.arqunn.usatoday.domain.usecase

import com.arqunn.usatoday.domain.repository.NewsRepository

class GetAllNews(
    private val repository: NewsRepository
) {
    operator fun invoke() {

    }
}