package com.maxbt.newsapplication.model.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maxbt.newsapplication.model.entity.news.Title


class TitleConverter {
    @TypeConverter
    fun fromTitle(title: Title): String = Gson().toJson(title)

    @TypeConverter
    fun toTitle(titleString: String): Title? = Gson().fromJson(titleString, Title::class.java)
}