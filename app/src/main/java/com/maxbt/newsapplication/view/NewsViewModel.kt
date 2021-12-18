package com.maxbt.newsapplication.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxbt.newsapplication.model.entity.Category
import com.maxbt.newsapplication.model.entity.News
import com.maxbt.newsapplication.model.repository.NewsRepository
import com.maxbt.newsapplication.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * This is main model that handling messaging
 * between database(NewsRepository) and Views(NewsFragment, SearchFragment etc.)
 */
class NewsViewModel(
    private val newsRepository : NewsRepository
) : ViewModel() {

    val news = MutableLiveData<Resource<List<News>>>()
    val categories = MutableLiveData<Resource<List<Category>>>()

    var selectedCategory : Category = Category()
    var searchQuery : String = ""

    private val newsPage = 1
    private var getNewsJob : Job? = null
    init {
        getCategories()
        searchNews()
    }

    fun searchNews(search : String = searchQuery, category: Category = selectedCategory){
        getNewsJob?.cancel()
        getNewsJob = viewModelScope.launch {

            news.postValue(Resource.Loading())

            val response = newsRepository.searchNews(
                category = category,
                search = search,
                page = newsPage)

            news.postValue(handleTResponse(response))
        }
    }


    private fun getCategories(amount : Int = 50) = viewModelScope.launch {
        categories.postValue(Resource.Loading())
        val response = newsRepository.getCategories(amount)
        categories.postValue(handleTResponse(response))
    }

    // This is made for handling any response and according to it change fragments/activities
    // For example if Resource.Loading() -> show gif, if Resource.Success() - show data
    private fun <T> handleTResponse(response : Response<List<T>>) : Resource<List<T>>{
        if (response.isSuccessful) {
            response.body()?.let { successResponse ->
                return Resource.Success(successResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
