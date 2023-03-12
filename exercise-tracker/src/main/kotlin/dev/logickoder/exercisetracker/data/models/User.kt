package dev.logickoder.exercisetracker.data.models

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "_User")
data class User(@Id var username: String)
