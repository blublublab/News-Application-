package com.maxbt.newsapplication.util

/**
 * This is util class made for handling response
 * from viewModel to fragments/activity , and according
 * to it change fragments behaviour
 * (Ex. do logic of opening browser on Success, show error Dialog window on error
 */


sealed class Resource<T> (
    val data : T? = null,
    val message : String? = null
){
    class Success<T>(data : T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}