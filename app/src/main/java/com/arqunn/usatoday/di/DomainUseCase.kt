package com.arqunn.usatoday.di

import com.arqunn.usatoday.domain.repository.NewsRepository
import com.arqunn.usatoday.domain.usecase.GetAllNews
import com.arqunn.usatoday.domain.usecase.NewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainUseCase {

    @Provides
    @Singleton
    fun provideNewsUseCases(repository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getAllNews = GetAllNews(repository)
        )
    }
}