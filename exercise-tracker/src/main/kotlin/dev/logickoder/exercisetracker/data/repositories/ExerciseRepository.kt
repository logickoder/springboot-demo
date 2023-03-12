package dev.logickoder.exercisetracker.data.repositories

import dev.logickoder.exercisetracker.data.models.Exercise
import org.springframework.data.repository.CrudRepository

interface ExerciseRepository : CrudRepository<Exercise, Long>