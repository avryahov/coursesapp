package com.avryahov.coursesapp.presentation.onboarding

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.avryahov.coursesapp.R
import com.avryahov.coursesapp.data.model.CourseTag
import com.avryahov.coursesapp.data.model.OnboardingLineData
import com.avryahov.coursesapp.data.model.TagStyle

class OnboardingViewModel : ViewModel() {

    fun getOnboardingLines(): List<OnboardingLineData> {
        return listOf(
            OnboardingLineData(
                xOffset = (-35).dp,
                tags = listOf(
                    CourseTag(R.string.course_1c_admin, TagStyle.DEFAULT, width = 240.dp),
                    CourseTag(
                        R.string.course_rabbitmq,
                        TagStyle.ACCENT,
                        rotation = -15f,
                        width = 130.dp,
                        height = 70.dp
                    ),
                    CourseTag(R.string.course_traffic, TagStyle.DEFAULT, width = 112.dp)
                )
            ),
            OnboardingLineData(
                xOffset = (-75).dp,
                tags = listOf(
                    CourseTag(R.string.course_content_marketing, TagStyle.DEFAULT, width = 200.dp),
                    CourseTag(R.string.course_b2b_marketing, TagStyle.DEFAULT, width = 190.dp),
                    CourseTag(R.string.course_google_analytics, TagStyle.DEFAULT, width = 180.dp)
                )
            ),
            OnboardingLineData(
                xOffset = (-55).dp,
                tags = listOf(
                    CourseTag(R.string.course_ux_researcher, TagStyle.DEFAULT, width = 200.dp),
                    CourseTag(R.string.course_web_analytics, TagStyle.DEFAULT, width = 180.dp),
                    CourseTag(
                        R.string.course_big_data,
                        TagStyle.ACCENT,
                        rotation = 10f,
                        width = 170.dp,
                        height = 70.dp
                    )
                )
            ),
            OnboardingLineData(
                xOffset = (-95).dp,
                tags = listOf(
                    CourseTag(R.string.course_game_design, TagStyle.DEFAULT, width = 130.dp),
                    CourseTag(R.string.course_web_design, TagStyle.DEFAULT, width = 125.dp),
                    CourseTag(R.string.course_cinema4d, TagStyle.DEFAULT, width = 125.dp),
                    CourseTag(R.string.course_prompt_engineering, TagStyle.DEFAULT, width = 220.dp)
                )
            ),
            OnboardingLineData(
                xOffset = (-70).dp,
                tags = listOf(
                    CourseTag(R.string.course_webflow, TagStyle.DEFAULT, width = 100.dp),
                    CourseTag(
                        R.string.course_threejs,
                        TagStyle.ACCENT,
                        rotation = -10f,
                        width = 110.dp,
                        height = 70.dp
                    ),
                    CourseTag(R.string.course_parsing, TagStyle.DEFAULT, width = 110.dp),
                    CourseTag(R.string.course_python_dev, TagStyle.DEFAULT, width = 200.dp)
                )
            )
        )
    }
}