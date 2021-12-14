package com.maxbt.newsapplication.model.api

import com.maxbt.newsapplication.model.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Simple client to get api for calling it later in NewsRepository
 * also okHttpClient for some else queries which is public
 *
 */
object RetrofitClient {

    val okHttpClient  by lazy{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy {

        return@lazy Retrofit.Builder()
            .baseUrl(Constants.SITE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val api: WordpressApi = retrofit.create(WordpressApi::class.java)
}