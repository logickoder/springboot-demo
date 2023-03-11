package dev.logickoder.coffee

import org.springframework.data.repository.CrudRepository

interface CoffeeRepository : CrudRepository<Coffee, String>