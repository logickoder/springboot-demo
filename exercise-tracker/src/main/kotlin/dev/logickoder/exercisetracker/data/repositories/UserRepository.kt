package dev.logickoder.exercisetracker.data.repositories

import dev.logickoder.exercisetracker.data.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String>