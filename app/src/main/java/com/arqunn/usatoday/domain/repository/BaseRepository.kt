package com.arqunn.usatoday.domain.repository

import com.arqunn.usatoday.data.local.DaoResult
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
                networkReturn =
                    ApiResult.Error(IOException(response.errorBody()?.string().orEmpty()))
            }
        } catch (err: IllegalArgumentException) {
            networkReturn = ApiResult.Error(err)
        }
        return networkReturn
    }

    suspend fun <T> localCall(
        call: suspend () -> List<T>?
    ): DaoResult {
        var localReturn = DaoResult(isSuccess = true, null)
        try {
            val response = call.invoke()
            response.isNullOrEmpty().letOnFalse {
                localReturn = DaoResult(true, response)
            }.letOnTrue {
                localReturn = DaoResult(false, Exception("An unexpected error occurred"))
            }
        } catch (err: IllegalArgumentException) {
            DaoResult(false, err)
        }
        return localReturn
    }

    suspend fun localCallInsert(
        call: suspend () -> Unit
    ): DaoResult {
        val localReturn: DaoResult = try {
            call.invoke()
            DaoResult(true, "The operation completed successfully")
        } catch (err: IllegalArgumentException) {
            DaoResult(false, err)
        }
        return localReturn
    }
}