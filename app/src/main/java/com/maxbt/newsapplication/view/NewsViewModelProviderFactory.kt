package com.maxbt.newsapplication.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maxbt.newsapplication.model.repository.NewsRepository
import java.lang.Exception


/**
Factory solving the problem that view model
can't accept arguments in primary constructor
 */

class NewsViewModelProviderFactory(
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}