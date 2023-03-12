package dev.logickoder.exercisetracker.data.models

import dev.logickoder.exercisetracker.data.converters.DurationConverter
import dev.logickoder.exercisetracker.data.converters.TimeConverter
import jakarta.persistence.*
import java.time.LocalDateTime
import kotlin.time.Duration

@Entity
data class Exercise(
    @Id @GeneratedValue var id: Long = 0L,
    @Transient var username: String,
    var description: String,
    @Convert(converter = DurationConverter::class)
    var duration: Duration,
    @Convert(converter = TimeConverter::class)
    var date: LocalDateTime,
)
