package dev.logickoder.planefinder

import org.springframework.data.repository.CrudRepository

interface AircraftRepository : CrudRepository<Aircraft, Long>