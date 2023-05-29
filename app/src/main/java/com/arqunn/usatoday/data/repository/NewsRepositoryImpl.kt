package com.arqunn.usatoday.data.repository

import com.arqunn.usatoday.data.local.ArticleDao
import com.arqunn.usatoday.data.remote.NewsApiClient
import com.arqunn.usatoday.data.remote.mapper.NewsMapper
import com.arqunn.usatoday.data.remote.model.NewsResponseDto
import com.arqunn.usatoday.data.remote.util.ApiResult
import com.arqunn.usatoday.di.IODispatcher
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.domain.model.NewsResponse
import com.arqunn.usatoday.domain.repository.NewsRepository
import com.arqunn.usatoday.util.extensions.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsRepositoryImpl(
    private val api: NewsApiClient,
    private val dao: ArticleDao,
    private val newsMapper: NewsMapper,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : NewsRepository {
    override fun getAllNews(): Flow<ApiResult<NewsResponse>> = flow {
        emit(ApiResult.Loading)
        networkCall {
            api.getAllNews()
        }.let { apiResult ->
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                (apiResult.getResult() as? NewsResponseDto)?.let { dto ->
                    val favArticles = getAllFavorites().first().map { it.uuid }
                    val newsResponse = newsMapper.mapToDomainModel(dto).also {
                        it.articles.forEach { article ->
                            article.isMyFavorite = favArticles.contains(article.url).toInt()
                        }
                    }
                    emit(ApiResult.Success(newsResponse))
                }
            }.letOnFalseOnSuspend {
                emit(ApiResult.Error(Exception("Oops! an unexpected error occurred.")))
            }
        }
    }.flowOn(ioDispatcher)

    override fun getAllFavorites(): Flow<List<Article>> {
        return dao.getAllFavorites()
    }

    override suspend fun addToFavorites(article: Article) {
        dao.addToFavorites(article)
    }

    override suspend fun removeFromFavorites(article: Article) {
        dao.removeFromFavorites(article)
    }

    override fun searchForNews(query: String): Flow<ApiResult<NewsResponse>> = flow {
        emit(ApiResult.Loading)
        networkCall {
            api.searchForNews(query)
        }.let { apiResult ->
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                (apiResult.getResult() as? NewsResponseDto)?.let { dto ->
                    emit(ApiResult.Success(newsMapper.mapToDomainModel(dto)))
                }
            }.letOnFalseOnSuspend {
                emit(ApiResult.Error(Exception("Oops! an unexpected error occurred.")))
            }
        }
    }.flowOn(ioDispatcher)
}