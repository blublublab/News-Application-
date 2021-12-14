package com.maxbt.newsapplication.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.maxbt.newsapplication.model.entity.News


/**
 * This class is need to operate with Room database (but there is only commands,
 * realization in NewsRepository Class)
 *
 */
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(news : News): Long

    @Delete
    suspend fun deleteNews(news : News)

    @Query("SELECT * FROM news")
    fun getNews() : LiveData<List<News>>
}