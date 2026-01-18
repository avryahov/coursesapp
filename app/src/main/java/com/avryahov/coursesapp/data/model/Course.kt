package com.avryahov.coursesapp.data.model

import androidx.annotation.DrawableRes

data class Course(
    val id: String,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String,
    @field:DrawableRes val bannerResId: Int
) {
    val priceAsInt: Int
        get() = price
            .replace(" ", "")
            .replace("₽", "")
            .replace("\u00A0", "")
            .toIntOrNull() ?: 0
}