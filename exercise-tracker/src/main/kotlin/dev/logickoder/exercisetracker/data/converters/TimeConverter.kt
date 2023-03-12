package dev.logickoder.exercisetracker.data.converters

import jakarta.persistence.AttributeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset

object TimeConverter : AttributeConverter<LocalDateTime, Long> {
    override fun convertToDatabaseColumn(attribute: LocalDateTime?): Long? {
        return attribute?.toEpochSecond(ZoneOffset.UTC)
    }

    override fun convertToEntityAttribute(dbData: Long?): LocalDateTime? {
        if (dbData == null) return null

        return LocalDateTime.ofEpochSecond(dbData, 0, ZoneOffset.UTC)
    }
}