package com.maxbt.newsapplication.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maxbt.newsapplication.model.repository.NewsRepository
import java.lang.ClassCastException
import java.lang.Exception


/**
Factory solving the problem that view model
can't accept arguments in primary constructor
 */

class NewsViewModelProviderFactory(
    private val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return NewsViewModel(newsRepository) as T
    }
}