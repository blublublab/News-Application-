package com.maxbt.newsapplication.model.entity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "news")

data class News(
    @PrimaryKey(autoGenerate = false)
    val id: Long,


    @SerializedName("_links")
    var links: Link,


    var title: Title,

    @SerializedName("categories")
    val category: List<Int>,

    val content: Content,
) {

    var date : String = ""
        get() {
        val currentDate = LocalDate.parse(field, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        return  "${currentDate.dayOfMonth} ${currentDate.month.name}"
    }
}