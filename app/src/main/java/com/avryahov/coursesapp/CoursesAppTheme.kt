package com.avryahov.coursesapp;

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Main theme for the CoursesApp
 */
@Composable
fun CoursesAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = DarkColorScheme, typography = Typography, content = content)
}

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF12B956),      // Green — кнопка
    onPrimary = Color(0xFFFFFFFF),     // White — текст на кнопке

    secondary = Color(0xFF32333A),     // Dark gray — теги
    onSecondary = Color(0xFFFFFFFF),   // White — текст на тегах

    background = Color(0xFF121212),    // Dark — фон
    onBackground = Color(0xFFFFFFFF),  // White — текст на фоне

    surface = Color(0xFF121212),       // Surface — как фон
    onSurface = Color(0xFFFFFFFF),     // White — текст на surface

    outline = Color(0xFF71787C),        // Stroke — границы
)

private val Typography = Typography(
    // "Ag Headline 28/36"
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    // "Ag Title large 22/28"
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    // "Ag Title medium 16/18"
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    // "Ag Body medium 14/20"
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    // "Ag Body small 12/16"
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    // "Ag Body button small 12/15"
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.sp
    ),
    // "Ag Caption 12/14"
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp
    )
)