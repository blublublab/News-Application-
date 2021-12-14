package com.maxbt.newsapplication.model.db

import android.content.Context
import androidx.room.*
import com.maxbt.newsapplication.model.db.converter.ContentConverter
import com.maxbt.newsapplication.model.db.converter.LinkConverter
import com.maxbt.newsapplication.model.db.converter.ListIntConverter
import com.maxbt.newsapplication.model.db.converter.TitleConverter
import com.maxbt.newsapplication.model.entity.News

/**
 * Abstract class needed to instantiate Room database
 * There are thre needed things for this database
 *
 * Class instantiator (NewsDatabase)
 * Dao to speak with db ( NewsDao )
 * and Entities such as (News, Link)
 * and Converter factories to serialize subclasses of class
 *
 */
@Database(
    entities = [News::class],
    version = 1
)
@TypeConverters(
    LinkConverter::class,
    TitleConverter::class,
    ContentConverter::class,
    ListIntConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    companion object{
        @Volatile
        private var instance: NewsDatabase? = null

        private fun getDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                NewsDatabase::class.java,
                "news_db.db")
                    .build()

        operator fun invoke(context : Context) : NewsDatabase =
            instance ?: getDatabase(context.applicationContext)
    }
    abstract fun getNewsDao() : NewsDao
}