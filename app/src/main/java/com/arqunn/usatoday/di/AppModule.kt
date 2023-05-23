package com.arqunn.usatoday.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.arqunn.usatoday.AndroidApp
import com.arqunn.usatoday.data.local.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext app: Context): AndroidApp = app as AndroidApp

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): ArticleDatabase {
        return Room.databaseBuilder(
            app,
            ArticleDatabase::class.java,
            ArticleDatabase.DATABASE_NAME
        ).build()
    }
}