package com.arqunn.usatoday.data.remote

import com.arqunn.usatoday.BuildConfig
import com.arqunn.usatoday.data.remote.model.NewsResponseDto
import com.arqunn.usatoday.data.remote.util.NetworkConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiClient {

    @GET("v2/top-headlines")
    suspend fun getAllNews(
        @Query("country")
        countryCode: String = NetworkConstants.COUNTRY_KEY,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<NewsResponseDto>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("language")
        language: String = NetworkConstants.LANGUAGE_KEY,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<NewsResponseDto>
}