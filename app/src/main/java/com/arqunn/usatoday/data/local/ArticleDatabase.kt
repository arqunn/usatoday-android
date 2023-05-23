package com.arqunn.usatoday.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arqunn.usatoday.domain.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract val articleDao: ArticleDao

    companion object {
        const val DATABASE_NAME = "article_db"
    }
}