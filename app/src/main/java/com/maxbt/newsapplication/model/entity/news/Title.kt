package com.maxbt.newsapplication.model.entity.news

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Title(
    @SerializedName("rendered")
    var title : String

) : Parcelable