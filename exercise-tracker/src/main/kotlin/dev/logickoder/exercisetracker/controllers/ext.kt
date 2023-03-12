package dev.logickoder.exercisetracker.controllers

import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun String?.toLocalDate(action: () -> LocalDate = { LocalDate.now() }) = if (isNullOrBlank()) {
    action()
} else LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)