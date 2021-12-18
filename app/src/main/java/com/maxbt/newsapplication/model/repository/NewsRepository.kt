package com.maxbt.newsapplication.model.repository

import com.maxbt.newsapplication.model.api.RetrofitClient
import com.maxbt.newsapplication.model.db.NewsDatabase
import com.maxbt.newsapplication.model.entity.Category
import com.maxbt.newsapplication.model.entity.News
import retrofit2.Response

/**
 * This is main class that would be speak between database and ViewModel
 */
class NewsRepository(
    val db : NewsDatabase
) {
    suspend fun searchNews(search : String = "",
                           page : Int = 1,
                           perPage : Int = 20,
                           category: Category = Category()
    ): Response<List<News>> {

        val searchNews = RetrofitClient.api.searchNews(
            search = search,
            page = page,
            category = category.id,
            perPage = perPage,
            embed = true)

        setImageUrl(searchNews)
        return searchNews
    }

    suspend fun getCategories(amount : Int = 50)  = RetrofitClient.api.getCategories(amount)



    //Translate image to get image url from api
    private suspend fun setImageUrl(responseNews : Response<List<News>>){
        responseNews.body()?.forEach { article ->
            val newsMedia = RetrofitClient.api.getMediaByNewsId(article.id)
            article.imageUrl = newsMedia.body()
                ?.takeIf { it.isNotEmpty() }
                ?.get(0)
                ?.guid
                ?.mainImageUrl ?: "http://image_not_available"
        }
    }
}