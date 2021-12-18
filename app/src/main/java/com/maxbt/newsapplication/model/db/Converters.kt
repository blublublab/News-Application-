package com.maxbt.newsapplication.model.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maxbt.newsapplication.model.entity.news.Content
import com.maxbt.newsapplication.model.entity.news.Title

sealed class Converters {
    companion object{
        class TitleConverter {
            @TypeConverter
            fun fromTitle(title: Title): String = Gson().toJson(title)

            @TypeConverter
            fun toTitle(titleString: String): Title? = Gson().fromJson(titleString, Title::class.java)
        }

        class ListIntConverter {
            @TypeConverter
            fun fromList(list: List<Int>?): String = Gson().toJson(list)

            @TypeConverter
            fun toList(list: String): List<Int> =  Gson().fromJson(list, Array<Int>::class.java).toList()
        }

        class ContentConverter {
            @TypeConverter
            fun fromContent(content : Content): String = Gson().toJson(content)

            @TypeConverter
            fun toContent(contentString: String): Content? = Gson().fromJson(contentString, Content::class.java)
        }
    }

}