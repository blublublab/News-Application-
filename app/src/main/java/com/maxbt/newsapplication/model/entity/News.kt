package com.maxbt.newsapplication.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val date : String,
    val content : String){

    var title: String = ""
        set(value) {
            field = value
                .replace("\\u2018", "\t‘")
                .replace("\\u2019", "\t‘")
        }
}