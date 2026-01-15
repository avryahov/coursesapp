package com.avryahov.coursesapp.ui.model

import androidx.annotation.DrawableRes

data class BottomNavItem(
    var label: String,
    @field:DrawableRes var iconResId: Int
)