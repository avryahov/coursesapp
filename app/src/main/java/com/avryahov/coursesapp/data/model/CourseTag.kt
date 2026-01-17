package com.avryahov.coursesapp.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.avryahov.coursesapp.ui.theme.AppColors

enum class TagStyle {
    DEFAULT,  // серые, прозрачные
    ACCENT    // зелёные, непрозрачные
}

data class CourseTag(
    val textRes: Int,
    val style: TagStyle = TagStyle.DEFAULT,
    val rotation: Float = 0f,
    val width: Dp = 200.dp,
    val height: Dp = 70.dp
) {
    val containerColor: Color
        get() = when (style) {
            TagStyle.DEFAULT -> AppColors.SecondaryDark
            TagStyle.ACCENT -> AppColors.PrimaryGreen
        }

    val isTransparent: Boolean
        get() = style == TagStyle.DEFAULT
}