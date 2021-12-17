package com.maxbt.newsapplication.model.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maxbt.newsapplication.model.entity.news.Content


class ContentConverter{
    @TypeConverter
    fun fromContent(content : Content): String = Gson().toJson(content)

    @TypeConverter
    fun toContent(contentString: String): Content? = Gson().fromJson(contentString, Content::class.java)
}