package com.maxbt.newsapplication.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maxbt.newsapplication.model.entity.News

@Database(
    entities = [News::class],
    version = 1
)
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