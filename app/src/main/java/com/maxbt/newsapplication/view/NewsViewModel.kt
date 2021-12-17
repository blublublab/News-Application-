package com.maxbt.newsapplication.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxbt.newsapplication.model.Constants
import com.maxbt.newsapplication.model.entity.Category
import com.maxbt.newsapplication.model.entity.News
import com.maxbt.newsapplication.model.repository.NewsRepository
import com.maxbt.newsapplication.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    private val newsRepository : NewsRepository
) : ViewModel() {

    val news = MutableLiveData<Resource<List<News>>>()
    val categories = MutableLiveData<Resource<List<Category>>>()

    lateinit var selectedCategory : Category
    lateinit var searchQuery : String

    private val newsPage = 1
    private var getNewsJob : Job? = null
    init {
        getCategory()
        getNewsByCategory(Category(
            id = Constants.DEFAULT_CATEGORY,
            count = 317,
            slug = "",
            name = ""))
    }

    fun getNewsByCategory(category: Category) {
        getNewsJob?.cancel()
        getNewsJob  = viewModelScope.launch {

            news.postValue(Resource.Loading())

            val response = newsRepository.getNewsByCategory(
                category,
                page = newsPage,
                perPage = 20,
                embed = true)

            news.postValue(handleTResponse(response))
        }
    }

    fun getNewsBySearch(search : String){
        getNewsJob?.cancel()
        getNewsJob = viewModelScope.launch {

            news.postValue(Resource.Loading())

            val response = newsRepository.searchNews(
                search = search,
                page = newsPage,
                perPage = 20)

            news.postValue(handleTResponse(response))
        }
    }


    private fun getCategory(amount : Int = 50) = viewModelScope.launch {
        categories.postValue(Resource.Loading())
        val response = newsRepository.getCategories(amount)
        categories.postValue(handleTResponse(response))
    }


    private fun <T> handleTResponse(response : Response<List<T>>) : Resource<List<T>>{
        if (response.isSuccessful) {
            response.body()?.let { successResponse ->
                return Resource.Success(successResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
