package com.avryahov.coursesapp.component.navigationbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.avryahov.coursesapp.ui.model.BottomNavItem

@Composable
fun BottomNavigationItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    val containerColor =
        if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.background

    val iconTint =
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground

    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        RoundedIconButton(
            iconResId = item.iconResId,
            contentDescription = item.label,
            onClick = onClick,
            containerColor = containerColor,
            iconTint = iconTint
        )

        Text(
            text = item.label,
            color = iconTint,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}