package com.avryahov.coursesapp.component.button

import androidx.compose.foundation.layout.Box
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
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconResId: Int,
    contentDescription: String,
    containerColor: Color = Color.Transparent,
    iconTint: Color = MaterialTheme.colorScheme.onBackground,
    cornerRadius: Dp = 16.dp,
    iconSize: Dp = 28.dp,
    iconPadding: Dp = 2.dp,
    enabled: Boolean = true,
) {
    Surface(
        shape = RoundedCornerShape(cornerRadius),
        color = containerColor,
        contentColor = iconTint,
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
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