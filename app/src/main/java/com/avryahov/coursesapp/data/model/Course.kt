package com.avryahov.coursesapp.data.model

import androidx.annotation.DrawableRes

data class Course(
    val id: Int,
    val title: String,
    val text: String,
    val price: Int,
    val rate: Float,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String,
    @field:DrawableRes val bannerResId: Int
)