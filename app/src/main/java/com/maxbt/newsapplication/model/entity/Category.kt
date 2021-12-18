package com.maxbt.newsapplication.model.entity

import com.maxbt.newsapplication.model.Constants

data class Category(
    val id: Long = Constants.DEFAULT_CATEGORY,
    val count: Int = id.toInt(),
    val name: String = "",
    val slug: String = name
)