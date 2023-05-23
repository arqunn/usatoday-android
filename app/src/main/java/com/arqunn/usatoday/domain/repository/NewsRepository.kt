package com.arqunn.usatoday.domain.repository

import com.arqunn.usatoday.data.remote.util.ApiResult
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.domain.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository : BaseRepository {

    fun getAllNews(): Flow<ApiResult<NewsResponse>>

    suspend fun getAllFavorites(): List<Article>
}
