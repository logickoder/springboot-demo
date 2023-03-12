package dev.logickoder.exercisetracker.data.converters

import jakarta.persistence.AttributeConverter
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

object DurationConverter : AttributeConverter<Duration, Long> {
    override fun convertToDatabaseColumn(attribute: Duration?): Long? {
        return attribute?.inWholeMinutes
    }

    override fun convertToEntityAttribute(dbData: Long?): Duration? {
        return dbData?.minutes
    }
}