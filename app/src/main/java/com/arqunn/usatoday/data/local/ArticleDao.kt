package com.arqunn.usatoday.data.local

import androidx.room.*
import com.arqunn.usatoday.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(article: Article)

    @Delete
    suspend fun removeFromFavorites(article: Article)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<Article>>

    @Query("SELECT * FROM favorites WHERE uuid = :id")
    suspend fun getFavoriteArticleById(id: String): Article?
}