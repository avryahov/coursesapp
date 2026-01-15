package com.avryahov.coursesapp.ui.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.borderTop(
    color: Color,
    strokeWidth: Dp = 1.5.dp
): Modifier {
    return this.then(
        Modifier.drawBehind {
            drawLine(
                color = color,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = strokeWidth.toPx()
            )
        }
    )
}