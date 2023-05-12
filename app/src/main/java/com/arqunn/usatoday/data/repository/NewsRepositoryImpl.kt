package com.arqunn.usatoday.data.repository

import com.arqunn.usatoday.data.remote.NewsApiClient
import com.arqunn.usatoday.data.remote.mapper.NewsMapper
import com.arqunn.usatoday.data.remote.model.NewsResponseDto
import com.arqunn.usatoday.data.remote.util.ApiResult
import com.arqunn.usatoday.di.IODispatcher
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
    private val newsMapper: NewsMapper,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : NewsRepository {
    override fun getAllNews(): Flow<ApiResult<NewsResponse>> = flow {
        emit(ApiResult.Loading)
        networkCall {
            api.getAllNews()
        }.let { apiResult ->
            apiResult.isSuccessAndNotNull().letOnTrueOnSuspend {
                emit(ApiResult.Success(newsMapper.mapToDomainModel(apiResult.getResult() as NewsResponseDto)))
            }.letOnFalseOnSuspend {
                emit(ApiResult.Error(Exception("Oops! an unexpected error occurred.")))
            }
        }
    }.flowOn(ioDispatcher)
}