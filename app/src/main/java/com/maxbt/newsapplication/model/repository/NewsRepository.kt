package com.maxbt.newsapplication.model.repository

import com.maxbt.newsapplication.model.api.RetrofitClient
import com.maxbt.newsapplication.model.db.NewsDatabase
import com.maxbt.newsapplication.model.entity.Category

/**
 * This is main class that would be speak between db and view
 */
class NewsRepository(
    val db : NewsDatabase
) {
    suspend fun getNewsByCategory(category: Category,
                                  page : Int,
                                  perPage : Int,
                                  embed : Boolean) =
        RetrofitClient.api.getNewsByCategory(category.id, page, perPage, embed)

}