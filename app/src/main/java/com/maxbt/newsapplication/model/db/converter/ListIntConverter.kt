package com.maxbt.newsapplication.model.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maxbt.newsapplication.model.entity.Link


class ListIntConverter {
    @TypeConverter
    fun fromList(list: List<Int>?): String = Gson().toJson(list)

    @TypeConverter
    fun toList(list: String): List<Int> =  Gson().fromJson(list, Array<Int>::class.java).toList()
}