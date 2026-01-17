package com.avryahov.coursesapp.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SocialButton(
    iconResId: Int,
    contentDescription: String,
    onClick: () -> Unit,
    containerColor: Color,
    modifier: Modifier = Modifier
) {
    RoundedIconButton(
        iconResId = iconResId,
        contentDescription = contentDescription,
        containerColor = containerColor,
        iconTint = Color.White,
        cornerRadius = 50.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick
    )
}