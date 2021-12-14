package com.maxbt.newsapplication.model.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maxbt.newsapplication.model.entity.Link

class LinkConverter {
    @TypeConverter
    fun fromLinks(links: Link): String {
        return Gson().toJson(links)
    }

    @TypeConverter
    fun toLinks(links: String): Link? {
        return Gson().fromJson(links, Link::class.java)
    }
}