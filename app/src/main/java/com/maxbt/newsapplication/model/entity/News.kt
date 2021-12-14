package com.maxbt.newsapplication.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = false)
    val id: Long,

    val date : String,

    @SerializedName("_links")
    var links :  Link,

    @SerializedName("categories")
    val category : Int,

    val content : String){

    var title: String = ""
        set(value) {
            field = value
                .replace("\\u2018", "\t‘")
                .replace("\\u2019", "\t‘")
        }
}