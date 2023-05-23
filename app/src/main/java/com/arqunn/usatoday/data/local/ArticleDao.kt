package com.arqunn.usatoday.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arqunn.usatoday.domain.model.Article
import java.util.concurrent.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleList(articleList: List<Article>)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<Article>

    @Query("SELECT * FROM articles WHERE isMyFav = 1")
    suspend fun getAllFavorites(): List<Article>
}