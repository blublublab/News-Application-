package com.maxbt.newsapplication.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.maxbt.newsapplication.model.entity.news.Content
import com.maxbt.newsapplication.model.entity.news.Title
import kotlinx.parcelize.Parcelize
/**
 * This is main entity that handling any other child entities in folder entity
 * Any other sub-entity of entity is laying down in the folder according to name of parent entity
 * (Ex.  news package for News, news_media package for NewsMedia
 */

@Entity(tableName = "news")
@Parcelize
data class News(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    var title: Title,
    val content: Content,
    @Expose
    var imageUrl : String = "",

    var date : String = ""
) : Parcelable