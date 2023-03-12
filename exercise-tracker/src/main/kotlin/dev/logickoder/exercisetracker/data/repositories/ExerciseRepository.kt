package dev.logickoder.exercisetracker.data.repositories

import dev.logickoder.exercisetracker.data.models.Exercise
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

interface ExerciseRepository : CrudRepository<Exercise, Long> {

    fun findByDateBetween(from: LocalDate, to: LocalDate): List<Exercise>
}