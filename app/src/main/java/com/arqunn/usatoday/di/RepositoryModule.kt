package com.arqunn.usatoday.di

import com.arqunn.usatoday.data.repository.NewsRepositoryImpl
import com.arqunn.usatoday.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(): NewsRepository {
        return NewsRepositoryImpl()
    }
}