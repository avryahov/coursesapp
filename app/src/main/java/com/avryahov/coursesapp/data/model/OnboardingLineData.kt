package com.avryahov.coursesapp.data.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val horizontalSpacing = 8.dp

data class OnboardingLineData(
    val xOffset: Dp,
    val tags: List<CourseTag>
) {
    fun calculateTagOffsets(): List<Dp> {
        val offsets = mutableListOf<Dp>()
        var currentX = xOffset
        for (tag in tags) {
            offsets.add(currentX)
            currentX += tag.width + horizontalSpacing
        }
        return offsets
    }
}