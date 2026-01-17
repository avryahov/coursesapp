package com.avryahov.coursesapp;

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.avryahov.coursesapp.ui.theme.AppColors

val RobotoFontFamily = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium)
)

private val Typography = Typography(
    // "Ag Headline 28/36"
    headlineLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    // "Ag Title large 22/28"
    titleLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    // "Ag Title medium 16/18"
    titleMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    // "Ag Body medium 14/20"
    bodyMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    // "Ag Body small 12/16"
    bodySmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    // "Ag Body button small 12/15"
    labelLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.sp
    ),
    // "Ag Caption 12/14"
    labelSmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp
    )
)

private val DarkColorScheme = darkColorScheme(
    primary = AppColors.PrimaryGreen,
    onPrimary = AppColors.OnPrimary,

    secondary = AppColors.SecondaryDark,
    onSecondary = AppColors.OnSecondary,

    background = AppColors.BackgroundDark,
    onBackground = AppColors.OnBackground,

    surface = AppColors.BackgroundDark,
    onSurface = AppColors.OnSurface,

    outline = AppColors.Outline
)

/**
 * Main theme for the CoursesApp
 */
@Composable
fun CoursesAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = DarkColorScheme, typography = Typography, content = content)
}