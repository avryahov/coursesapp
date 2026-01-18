package com.avryahov.coursesapp.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.avryahov.coursesapp.data.model.Course
import com.avryahov.coursesapp.ui.theme.AppColors

@Composable
fun CourseCard(
    course: Course,
    onToggleLike: (Int) -> Unit,
    onNavigateToCourse: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(AppColors.BlockBackground)
            .padding(12.dp)
    ) {
        TopSection(course = course) {
            onToggleLike(course.id)
        }

        BottomSection(course = course) {
            onNavigateToCourse(course.id)
        }
    }
}