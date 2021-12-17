package com.maxbt.newsapplication.model.entity

data class Category(
    val id: Long,
    val count: Int,
    val name: String,
    val slug: String
)