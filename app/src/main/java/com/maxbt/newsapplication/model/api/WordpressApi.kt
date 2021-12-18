package com.maxbt.newsapplication.model.api

import com.maxbt.newsapplication.model.entity.Category
import com.maxbt.newsapplication.model.entity.News
import com.maxbt.newsapplication.model.entity.NewsMedia
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Rest Retrofit API for query requests from server to db
 */
interface WordpressApi {

    @GET("/wp-json/wp/v2/posts")
    suspend fun searchNews(
        @Query("search") search: String,
        @Query("categories") category:Long,

        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
        @Query("_embed") embed: Boolean = true
    ):Response<List<News>>

    @GET("/wp-json/wp/v2/categories")
    suspend fun getCategories(
        @Query("per_page") perPage: Int = 50
    ):Response<List<Category>>

    @GET("/wp-json/wp/v2/media")
    suspend fun getMediaByNewsId(
            @Query("parent") id : Long)
     : Response<List<NewsMedia>>
}

