package dev.logickoder.exercisetracker.data.converters

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.time.LocalDate

@Converter(autoApply = true)
object DateConverter : AttributeConverter<LocalDate?, String?>, JsonSerializer<LocalDate>() {
    override fun convertToDatabaseColumn(attribute: LocalDate?): String? {
        return attribute?.toString()
    }

    override fun convertToEntityAttribute(dbData: String?): LocalDate? {
        if (dbData == null) return null

        return LocalDate.parse(dbData)
    }

    override fun serialize(value: LocalDate?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeRawValue(convertToDatabaseColumn(value))
    }
}