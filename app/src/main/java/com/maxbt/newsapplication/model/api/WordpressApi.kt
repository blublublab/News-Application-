package com.maxbt.newsapplication.model.api

import com.maxbt.newsapplication.model.entity.Category
import com.maxbt.newsapplication.model.entity.News
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * RestAPI for query requests from server to db
 */
interface WordpressApi {

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

    @GET("/wp-json/wp/v2/posts")
    suspend fun getNewsByCategory(
        @Query("categories") category:Int,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("_embed") embed: Boolean
    ) :List<News>

}