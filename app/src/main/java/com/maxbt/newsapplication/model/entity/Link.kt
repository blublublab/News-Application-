package com.maxbt.newsapplication.model.entity

import com.google.gson.annotations.SerializedName

data class Link(
   @SerializedName("wp:featuredmedia")
   val featuredMedia : List<FeaturedMedia>
)