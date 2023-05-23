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
import com.arqunn.usatoday.util.extensions.getResult
import com.arqunn.usatoday.util.extensions.isSuccessAndNotNull
import com.arqunn.usatoday.util.extensions.letOnFalseOnSuspend
import com.arqunn.usatoday.util.extensions.letOnTrueOnSuspend
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
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
        localCall {
            dao.getAllArticles()
        }.let { localResult ->
            localResult.isSuccess.letOnFalseOnSuspend {
                networkCall {
                    api.getAllNews()
                }.let { apiResult ->
                    apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                        (apiResult.getResult() as? NewsResponseDto)?.let {
                            val articles = it.articles?.map { article ->
                                newsMapper.mapToDomainModel(article)
                            }
                            localCallInsert { dao.insertArticleList(articles.orEmpty()) }
                            emit(ApiResult.Success(newsMapper.mapToDomainModel(it)))
                        }
                    }.letOnFalseOnSuspend {
                        emit(ApiResult.Error(Exception("Oops! an unexpected error occurred.")))
                    }
                }
            }.letOnTrueOnSuspend {
                val articles = (localResult.data as? List<Article>).orEmpty()
                emit(ApiResult.Success(
                    NewsResponse(
                        status = "ok",
                        totalResults = articles.size,
                        articles = articles
                    )
                ))
            }
        }
    }.flowOn(ioDispatcher)

    override fun getAllFavorites(): Flow<List<Article>> {
        return dao.getAllFavorites()
    }

    override suspend fun addUpdateFavorites(articleId: Int, isMyFavorite: Int) {
        dao.addUpdateFavorites(articleId, isMyFavorite)
    }
}