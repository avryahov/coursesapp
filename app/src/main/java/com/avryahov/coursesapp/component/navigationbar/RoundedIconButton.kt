package com.avryahov.coursesapp.component.navigationbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedIconButton(
    iconResId: Int,
    contentDescription: String,
    onClick: () -> Unit,
    containerColor: Color = Color.Transparent,
    iconTint: Color = MaterialTheme.colorScheme.onBackground,
    width: Dp = 80.dp,
    height: Dp = 36.dp,
    cornerRadius: Dp = 16.dp,
    iconSize: Dp = 28.dp,
    iconPadding: Dp = 2.dp,
    modifier: Modifier
) {
    Surface(
        shape = RoundedCornerShape(cornerRadius),
        color = containerColor,
        onClick = onClick,
        modifier = modifier.size(width = width, height = height)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = contentDescription,
                tint = iconTint,
                modifier = Modifier
                    .size(iconSize)
                    .padding(iconPadding)
            )
        }
    }
}