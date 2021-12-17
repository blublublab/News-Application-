package com.maxbt.newsapplication.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.maxbt.newsapplication.model.entity.news.Content
import com.maxbt.newsapplication.model.entity.news.Title
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = false)
    val id: Long,

   // @SerializedName("_links")
   // var links: Link,

    var title: Title,

    val content: Content,
) {
    @Expose
    var imageUrl  : String = ""

    var date : String = ""
        get() {
        val currentDate = LocalDate.parse(field, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        return  "${currentDate.dayOfMonth} ${currentDate.month.name}"
    }
}