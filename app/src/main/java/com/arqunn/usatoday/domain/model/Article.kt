package com.arqunn.usatoday.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arqunn.usatoday.util.base.adapter.ListAdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class Article(
    @PrimaryKey override val id: Int,
    val source: Source,
    val author: String,
    var title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val isMyFav: Boolean
): ListAdapterItem, Parcelable