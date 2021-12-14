package com.maxbt.newsapplication.model.entity

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("rendered")
    val content : String,
    val protected : Boolean,
)