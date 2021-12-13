package com.maxbt.newsapplication.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.maxbt.newsapplication.model.entity.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(news : News): Int

    @Delete
    suspend fun deleteNews(news : News)

    @Query("SELECT * FROM news")
    fun getNews() : LiveData<List<News>>
}