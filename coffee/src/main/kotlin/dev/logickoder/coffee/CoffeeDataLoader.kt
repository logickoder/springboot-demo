package dev.logickoder.coffee

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class CoffeeDataLoader(private val repository: CoffeeRepository) {
    @PostConstruct
    private fun loadData() {
        repository.saveAll(listOf(Coffee(name = "Café Cereza"), Coffee(name = "Café Ganador"), Coffee(name = "Café Lareño"), Coffee(name = "Café Três Pontas")))
    }
}