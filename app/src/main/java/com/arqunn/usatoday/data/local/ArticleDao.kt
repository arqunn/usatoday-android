package com.arqunn.usatoday.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arqunn.usatoday.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleList(articleList: List<Article>)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<Article>

    @Query("SELECT * FROM articles WHERE isMyFavorite = 1")
    fun getAllFavorites(): Flow<List<Article>>

    @Query("UPDATE articles SET isMyFavorite = :isMyFavorite WHERE id = :articleId")
    suspend fun addUpdateFavorites(articleId: Int, isMyFavorite: Int)
}