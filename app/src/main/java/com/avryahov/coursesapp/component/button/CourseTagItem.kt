package com.avryahov.coursesapp.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.avryahov.coursesapp.data.model.CourseTag
import com.skydoves.cloudy.cloudy

@Composable
fun CourseTagItem(
    text: String,
    tag: CourseTag,
    modifier: Modifier = Modifier
) {
    var isHovered by remember { mutableStateOf(false) }

    val backgroundColor = if (isHovered) {
        tag.containerColor.copy(alpha = 1.0f)
    } else {
        tag.containerColor.copy(alpha = if (tag.isTransparent) 0.3f else 1.0f)
    }

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isHovered = true
                        tryAwaitRelease()
                        isHovered = false
                    }
                )
            }
            .graphicsLayer { rotationZ = tag.rotation }
            .clip(RoundedCornerShape(100))
            .background(backgroundColor)
            .cloudy(radius = if (isHovered) 21 else 0)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}