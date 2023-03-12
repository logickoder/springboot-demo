package dev.logickoder.exercisetracker.data.models

import java.time.LocalDateTime
import kotlin.time.Duration

data class Log(val description: String, val duration: Duration, val date: LocalDateTime)

data class Logs(val username: String, val count: Int, val logs: List<Log>)