package dev.logickoder.coffee

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/coffees")
class CoffeeController(private val repository: CoffeeRepository) {

    @GetMapping
    fun getCoffees(): Iterable<Coffee> = repository.findAll()

    @GetMapping("/{id}")
    fun getCoffee(@PathVariable id: String): Coffee? {
        return repository.findByIdOrNull(id)
    }

    @PostMapping
    fun postCoffee(@RequestBody coffee: Coffee): Coffee {
        repository.save(coffee)
        return coffee
    }

    @PutMapping("/{id}")
    fun putCoffee(@PathVariable id: String, @RequestBody coffee: Coffee): ResponseEntity<Coffee> {
        return if (repository.existsById(id)) {
            repository.save(coffee)
            ResponseEntity(coffee, HttpStatus.OK)
        } else ResponseEntity(postCoffee(coffee), HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteCoffee(@PathVariable id: String) {
        repository.deleteById(id)
    }
}