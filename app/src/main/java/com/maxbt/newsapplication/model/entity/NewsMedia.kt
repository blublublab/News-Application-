package com.maxbt.newsapplication.model.entity

import com.google.gson.annotations.SerializedName
import com.maxbt.newsapplication.model.entity.news_media.Guid

data class NewsMedia(
    @SerializedName("guid")
    val guid : Guid
)