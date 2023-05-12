package com.arqunn.usatoday.domain.repository

import com.arqunn.usatoday.data.remote.util.ApiResult
import com.arqunn.usatoday.util.extensions.isSuccessAndNotNull
import com.arqunn.usatoday.util.extensions.letOnFalse
import com.arqunn.usatoday.util.extensions.letOnTrue
import retrofit2.Response
import java.io.IOException

interface BaseRepository {

    suspend fun <T> networkCall(
        call: suspend () -> Response<T>
    ): ApiResult<T?> {
        var networkReturn: ApiResult<T?> = ApiResult.Loading
        try {
            val response = call.invoke()
            response.isSuccessAndNotNull().letOnTrue {
                networkReturn = ApiResult.Success(response.body())
            }.letOnFalse {
                networkReturn = ApiResult.Error(IOException(response.errorBody()?.string().orEmpty()))
            }
        } catch (e: IllegalArgumentException) {
            networkReturn = ApiResult.Error(e)
        }
        return networkReturn
    }
}