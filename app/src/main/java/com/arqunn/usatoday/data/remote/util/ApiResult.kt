package com.arqunn.usatoday.data.remote.util

sealed class ApiResult<out R> {
    object Loading : ApiResult<Nothing>()
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val error: Exception) : ApiResult<Nothing>()
}