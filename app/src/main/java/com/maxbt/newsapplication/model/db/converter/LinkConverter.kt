package com.maxbt.newsapplication.model.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maxbt.newsapplication.model.entity.Link

class LinkConverter {
    @TypeConverter
    fun fromLinks(links: Link): String = Gson().toJson(links)

    @TypeConverter
    fun toLinks(links: String): Link? = Gson().fromJson(links, Link::class.java)
}