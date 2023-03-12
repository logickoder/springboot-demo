package dev.logickoder.exercisetracker.data.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Transient
import java.time.LocalDate

@Entity
data class Exercise(
    @Id @GeneratedValue var id: Long = 0L,
    @Transient var username: String,
    var description: String,
    var duration: Int,
    var date: LocalDate,
)
