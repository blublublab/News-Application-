package com.maxbt.newsapplication.model.api

import com.maxbt.newsapplication.model.entity.Category
import com.maxbt.newsapplication.model.entity.News
import retrofit2.http.GET
import retrofit2.http.Query

interface WordPressAPI {

  @GET("/wp-json/wp/v2/posts")
  suspend fun getNews(
    @Query("search") search: String,
    @Query("page") page: Int = 1,
    @Query("per_page") perPage: Int = 10,
    @Query("_embed") embed: Boolean = true
  ):List<News>

  @GET("/wp-json/wp/v2/categories")
  suspend fun getCategories(
    @Query("per_page") perPage: Int = 50
  ):List<Category>
}