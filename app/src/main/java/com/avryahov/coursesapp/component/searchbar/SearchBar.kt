package com.avryahov.coursesapp.component.searchbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.component.button.RoundedIconButton

@Composable
fun SearchBar(
    onSearchChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SearchTextField(
            onSearchChange = onSearchChange,
            modifier = Modifier.weight(1f)
        )

        RoundedIconButton(
            onClick = onFilterClick,
            iconResId = R.drawable.ic_filter,
            contentDescription = stringResource(R.string.filter_content_description),
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.7f),
            iconTint = MaterialTheme.colorScheme.onSurface,
            cornerRadius = 28.dp,
            iconSize = 24.dp,
            iconPadding = 0.dp,
            modifier = Modifier
                .height(56.dp)
                .width(56.dp)
        )
    }
}