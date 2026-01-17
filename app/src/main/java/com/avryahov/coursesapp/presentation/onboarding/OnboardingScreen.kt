package com.avryahov.coursesapp.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.component.button.ActionButton
import com.avryahov.coursesapp.component.button.CourseTagItem

@Composable
fun OnboardingScreen(
    onContinueClick: () -> Unit,
    viewModel: OnboardingViewModel = viewModel()
) {
    val lines = remember { viewModel.getOnboardingLines() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .graphicsLayer { clip = false }
    ) {

        Box(
            modifier = Modifier
                .padding(top = 160.dp, start = 16.dp, end = 16.dp, bottom = 50.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.onboarding_title),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            lines.forEach { line ->
                Box {
                    val offsets = line.calculateTagOffsets()
                    line.tags.forEachIndexed { index, tag ->
                        CourseTagItem(
                            text = stringResource(tag.textRes),
                            tag = tag,
                            modifier = Modifier
                                .offset(x = offsets[index], y = 0.dp)
                                .width(tag.width)
                                .height(tag.height)
                        )
                    }
                }
            }
        }

        ActionButton(
            text = stringResource(R.string.continue_button),
            onClick = onContinueClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 16.dp)
        )
    }
}