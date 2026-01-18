package com.avryahov.coursesapp.component.searchbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.avryahov.coursesapp.R

@Composable
fun SearchTextField(
    onSearchChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(R.string.search_content_description),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )

            TextField(
                value = "",
                onValueChange = onSearchChange,
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_courses_placeholder),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }
    }
}