package com.arqunn.usatoday.data.remote.model

import com.google.gson.annotations.SerializedName

data class NewsResponseDto(
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?,
    @SerializedName("articles")
    val articles: List<ArticleDto>?,
)