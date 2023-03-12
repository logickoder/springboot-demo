package dev.logickoder.exercisetracker.controllers

import dev.logickoder.exercisetracker.data.models.User
import dev.logickoder.exercisetracker.data.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val users: UserRepository) {
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
}