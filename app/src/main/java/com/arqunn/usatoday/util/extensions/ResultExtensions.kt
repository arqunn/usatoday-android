package com.arqunn.usatoday.util.extensions

import com.arqunn.usatoday.data.remote.util.ApiResult
import retrofit2.Response

fun Response<*>?.isSuccessAndNotNull(): Boolean = this?.let {
    it.body() != null && it.isSuccessful
} ?: run {
    false
}

fun ApiResult<*>?.isSuccessAndNotNull() =
    this is ApiResult.Success && this.data != null

fun ApiResult<*>?.getResult(): Any? = when (this) {
    is ApiResult.Success -> this.data
    else -> null
}
