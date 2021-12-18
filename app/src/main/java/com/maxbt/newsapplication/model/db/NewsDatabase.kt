package com.maxbt.newsapplication.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maxbt.newsapplication.model.entity.News

/**
 * Abstract class needed to instantiate Room database
 *
 * Needed things for this database:
 *
 *  Class instantiation (NewsDatabase)
 *  Dao to speak with db ( NewsDao )
 *  Entities such as (News)
 *  Converter factories to serialize subclasses of class
 *
 */
@Database(
    entities = [News::class],
    version = 1
)
@TypeConverters(
    Converters.Companion.TitleConverter::class,
    Converters.Companion.ListIntConverter::class,
    Converters.Companion.ContentConverter::class)
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