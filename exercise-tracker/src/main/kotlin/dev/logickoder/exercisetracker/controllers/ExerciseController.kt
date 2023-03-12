package dev.logickoder.exercisetracker.controllers

import dev.logickoder.exercisetracker.data.models.Exercise
import dev.logickoder.exercisetracker.data.repositories.ExerciseRepository
import dev.logickoder.exercisetracker.data.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

@RestController
class ExerciseController(private val users: UserRepository, private val exercises: ExerciseRepository) {

    @PostMapping("/api/users/{userId}/exercises")
    fun addExercise(
        body: AddExerciseRequest,
        @PathVariable userId: String,
    ): ResponseEntity<*> {
        val user = users.findByIdOrNull(userId) ?: return ResponseEntity.badRequest().body("User not found")

        if (body.description.isNullOrBlank()) {
            return ResponseEntity.badRequest().body("Description is required")
        }

        val duration = runCatching {
            if (body.duration.isNullOrBlank()) throw Exception("Duration is required")
            body.duration.toInt().minutes
        }.getOrElse {
            return ResponseEntity.badRequest().body(it.localizedMessage)
        }

        val date = run {
            if (body.date.isNullOrBlank()) {
                LocalDateTime.now()
            } else LocalDateTime.parse(body.date)
        }

        val exercise = exercises.save(
            Exercise(
                username = user.username,
                description = body.description,
                duration = duration,
                date = date,
            )
        )

        return ResponseEntity(exercise, HttpStatus.CREATED)
    }

    data class AddExerciseRequest(
        val description: String?,
        val duration: String?,
        val date: String?,
    )
}