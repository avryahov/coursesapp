package com.avryahov.coursesapp.data.model

import androidx.annotation.DrawableRes
import java.time.LocalDateTime

data class Course(
    val id: Int,
    val title: String,
    val text: String,
    val price: Int,
    val rate: Float,
    val startDate: LocalDateTime,
    val hasLike: Boolean,
    val publishDate: LocalDateTime,
    @field:DrawableRes val bannerResId: Int
)