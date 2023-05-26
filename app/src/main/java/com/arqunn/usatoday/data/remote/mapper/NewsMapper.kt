package com.arqunn.usatoday.data.remote.mapper

import com.arqunn.usatoday.data.remote.model.ArticleDto
import com.arqunn.usatoday.data.remote.model.NewsResponseDto
import com.arqunn.usatoday.domain.model.Article
import com.arqunn.usatoday.domain.model.NewsResponse
import com.arqunn.usatoday.domain.model.Source
import com.arqunn.usatoday.domain.util.DomainMapper
import com.arqunn.usatoday.util.extensions.orZero
import com.arqunn.usatoday.util.extensions.splitByDelimiter
import kotlin.random.Random

class NewsMapper: DomainMapper<NewsResponseDto, NewsResponse> {

    override fun mapToDomainModel(model: NewsResponseDto): NewsResponse {
        return NewsResponse(
            status = model.status.orEmpty(),
            totalResults = model.totalResults.orZero(),
            articles = model.articles?.map { articleDto ->
                mapToDomainModel(articleDto)
            }.orEmpty()
        )
    }

    private fun mapToDomainModel(model: ArticleDto): Article {
        return Article(
            uuid = model.url.orEmpty(),
            source = Source(
                id = model.source?.id ?: Random.nextInt(),
                name = model.source?.name.orEmpty()
            ),
            author = model.author.orEmpty(),
            title = model.title.orEmpty().splitByDelimiter(" - "),
            description = model.description.orEmpty(),
            url = model.url.orEmpty(),
            urlToImage = model.urlToImage.orEmpty(),
            publishedAt = model.publishedAt.orEmpty(),
            content = model.content.orEmpty(),
            id = Random.nextInt(),
        )
    }
}