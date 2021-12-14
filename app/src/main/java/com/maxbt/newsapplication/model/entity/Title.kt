package com.maxbt.newsapplication.model.entity

import com.google.gson.annotations.SerializedName


class Title {
    @SerializedName("rendered")
    var title : String = ""
        set(value) {
            field = value
                .replace("\\u2018", "\t‘")
                .replace("\\u2019", "\t‘")
        }
}