package com.arqunn.usatoday.domain.model

import com.arqunn.usatoday.util.base.adapter.ListAdapterItem

data class Article(
    override val id: Int,
    val source: Source,
    val author: String,
    var title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
): ListAdapterItem