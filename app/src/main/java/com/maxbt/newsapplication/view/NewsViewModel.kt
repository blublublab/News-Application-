package com.maxbt.newsapplication.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxbt.newsapplication.model.entity.Category
import com.maxbt.newsapplication.model.entity.News
import com.maxbt.newsapplication.model.repository.NewsRepository
import com.maxbt.newsapplication.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository : NewsRepository
) : ViewModel() {
    val news = MutableLiveData<Resource<News>>()
    val newsPage = 1

    fun getNews(category: Category) = viewModelScope.launch {
        news.postValue(Resource.Loading())
        val response = newsRepository.getNewsByCategory(
            category,
            page = newsPage,
            perPage = 20,
            embed = true)

    }

    private fun handleNewsResponse(response: Response<News>): Resource<News> {
        if (response.isSuccessful) {
            response.body()?.let { successResponse ->
                return Resource.Success(successResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
