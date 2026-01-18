package com.avryahov.coursesapp.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.component.button.RoundedIconButton
import com.avryahov.coursesapp.data.model.Course
import com.avryahov.coursesapp.ui.theme.AppColors
import com.avryahov.coursesapp.util.DateFormatter

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    course: Course,
    onToggleLike: () -> Unit
) {
    val badgeContainerColor = AppColors.BlockBackground.copy(alpha = 0.5f)
    val badgeContentColor = MaterialTheme.colorScheme.onSurface

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        Image(
            painter = painterResource(id = course.bannerResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
        ) {
            InfoBadge(
                text = course.rate.toString(),
                iconResId = R.drawable.ic_star,
                textColor = badgeContentColor,
                iconTint = badgeContentColor,
                containerColor = badgeContainerColor
            )
            InfoBadge(
                text = DateFormatter.format(course.startDate),
                modifier = Modifier.padding(start = 8.dp),
                textColor = badgeContentColor,
                iconTint = badgeContentColor,
                containerColor = badgeContainerColor
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 12.dp, end = 12.dp)
        ) {
            RoundedIconButton(
                onClick = onToggleLike,
                iconResId = R.drawable.ic_favourite,
                contentDescription = stringResource(
                    id = if (course.hasLike) R.string.content_desc_remove_from_favourites
                    else R.string.content_desc_add_to_favourites
                ),
                containerColor = badgeContainerColor,
                iconTint = if (course.hasLike) AppColors.PrimaryGreen else badgeContentColor,
                cornerRadius = 20.dp,
                iconSize = 16.dp,
                modifier = Modifier
                    .size(28.dp)
            )
        }
    }
}

