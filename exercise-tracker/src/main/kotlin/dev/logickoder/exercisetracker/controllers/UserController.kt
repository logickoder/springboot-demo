package dev.logickoder.exercisetracker.controllers

import dev.logickoder.exercisetracker.data.models.Log
import dev.logickoder.exercisetracker.data.models.Logs
import dev.logickoder.exercisetracker.data.models.User
import dev.logickoder.exercisetracker.data.repositories.ExerciseRepository
import dev.logickoder.exercisetracker.data.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/users")
class UserController(private val users: UserRepository, private val exercises: ExerciseRepository) {
    @GetMapping
    fun getUsers(): Iterable<User> = users.findAll()

    @PostMapping
    fun createUser(body: User): ResponseEntity<*> {
        if (body.username.isBlank()) {
            return ResponseEntity.badRequest().body("User cannot be empty")
        }

        var user = users.findByIdOrNull(body.username)

        val status = if (user == null) HttpStatus.CREATED else HttpStatus.OK

        if (user == null) {
            user = users.save(body)
        }

        return ResponseEntity(user, status)
    }

    @GetMapping("/{username}/logs")
    fun getLogs(
        @PathVariable username: String,
        @RequestParam(required = false) from: String?,
        @RequestParam(required = false) to: String?,
        @RequestParam(required = false) limit: String?,
    ): ResponseEntity<*> {
        val user = users.findByIdOrNull(username)
            ?: return ResponseEntity.badRequest().body("User does not exist")

        var items = exercises.findByDateBeforeAndDateAfter(
            from.toLocalDate { LocalDate.MIN },
            to.toLocalDate { LocalDate.MAX },
        )

        if (limit?.toIntOrNull() != null) {
            items = items.chunked(limit.toInt()).first()
        }

        val logs = items.map {
            Log(it.description, it.duration, it.date)
        }

        return ResponseEntity.ok(Logs(user.username, logs.size, logs))
    }
}