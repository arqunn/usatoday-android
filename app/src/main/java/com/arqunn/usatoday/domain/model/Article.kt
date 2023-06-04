package com.arqunn.usatoday.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arqunn.usatoday.util.base.adapter.ListAdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorites")
data class Article(
    @PrimaryKey val uuid: String,
    val source: Source,
    val author: String,
    var title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    override val id: Int,
): ListAdapterItem, Parcelable