package dev.logickoder.exercisetracker.data.models

import java.time.LocalDate

data class Log(val description: String, val duration: Int, val date: LocalDate)

data class Logs(val username: String, val count: Int, val logs: List<Log>)