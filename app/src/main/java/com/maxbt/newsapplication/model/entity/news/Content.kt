package com.maxbt.newsapplication.model.entity.news

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content(
    @SerializedName("rendered")
    val content : String,
    val protected : Boolean,
) : Parcelable