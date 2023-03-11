package dev.logickoder.urlshortner

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Url(@Id var original: String, var short: String)
