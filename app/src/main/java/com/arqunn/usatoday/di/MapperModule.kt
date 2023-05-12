package com.arqunn.usatoday.di

import com.arqunn.usatoday.data.remote.mapper.NewsMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideNewsMapper() = NewsMapper()
}