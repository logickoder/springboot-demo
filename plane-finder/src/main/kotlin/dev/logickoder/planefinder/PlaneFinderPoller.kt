package dev.logickoder.planefinder

import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient


@EnableScheduling
@Component
class PlaneFinderPoller(
        private val connectionFactory: RedisConnectionFactory,
        private val repository: AircraftRepository,
) {
    private val client = WebClient.create("http://localhost:8080/aircraft")

    @Scheduled(fixedRate = 1000)
    private fun pollPlanes() {
        connectionFactory.connection.serverCommands().flushDb()

        client.get()
                .retrieve()
                .bodyToFlux(Aircraft::class.java)
                .filter { aircraft -> aircraft.reg.isNotEmpty() }
                .toStream()
                .forEach(repository::save)

        repository.findAll().forEach(::println)
    }
}