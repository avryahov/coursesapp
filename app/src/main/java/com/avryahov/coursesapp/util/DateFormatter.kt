package com.avryahov.coursesapp.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {
    private val formatter =
        DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.forLanguageTag("ru-RU"))

    fun format(date: LocalDateTime): String = date.format(formatter)
}