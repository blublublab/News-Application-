package com.maxbt.newsapplication.view

import android.util.Log
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
    private val newsRepository : NewsRepository
) : ViewModel() {

    val news = MutableLiveData<Resource<List<News>>>()
    private val newsPage = 1
    init {
        getNews(Category(317, 317, "", ""))
    }

    fun getNews(category: Category) = viewModelScope.launch {

        news.postValue(Resource.Loading())

        val response = newsRepository.getNewsByCategory(
            category,
            page = newsPage,
            perPage = 20,
            embed = true)

        news.postValue(handleNewsResponse(response))
    }

    private fun handleNewsResponse(response: Response<List<News>>): Resource<List<News>> {
        if (response.isSuccessful) {
            response.body()?.let { successResponse ->
                return Resource.Success(successResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
