package dev.logickoder.coffee

import java.util.*

data class Coffee(val id: String = UUID.randomUUID().toString(), val name: String)
