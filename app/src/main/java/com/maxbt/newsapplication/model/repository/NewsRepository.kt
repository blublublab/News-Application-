package com.maxbt.newsapplication.model.repository

import com.maxbt.newsapplication.model.api.RetrofitClient
import com.maxbt.newsapplication.model.db.NewsDatabase
import com.maxbt.newsapplication.model.entity.Category
import com.maxbt.newsapplication.model.entity.News
import retrofit2.Response

/**
 * This is main class that would be speak between db and view
 */
class NewsRepository(
    val db : NewsDatabase
) {
    suspend fun searchNews(search : String,
                           page : Int = 1,
                           perPage : Int = 20): Response<List<News>> {
        val searchNews = RetrofitClient.api.searchNews(search, page , perPage)
        setImageUrl(searchNews)
        return searchNews
    }

    suspend fun getCategories(amount : Int = 50)  = RetrofitClient.api.getCategories(amount)



    //Translate image to get image url from api
    private suspend fun setImageUrl(responseNews : Response<List<News>>){
        responseNews.body()?.forEach { singleNews ->
            val newsMedia = RetrofitClient.api.getMediaByNewsId(singleNews.id)
            singleNews.imageUrl = newsMedia.body()
                ?.takeIf { it.isNotEmpty() }
                ?.get(0)
                ?.guid
                ?.mainImageUrl ?: "http://image_not_available"
        }
    }
}