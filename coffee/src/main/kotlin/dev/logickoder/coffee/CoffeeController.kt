package dev.logickoder.coffee

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/coffees")
class CoffeeController {
    private val coffees = mutableListOf<Coffee>().apply {
        add(Coffee(name = "Café Cereza"))
        add(Coffee(name = "Café Ganador"))
        add(Coffee(name = "Café Lareño"))
        add(Coffee(name = "Café Três Pontas"))
    }

    @GetMapping
    fun getCoffees(): Iterable<Coffee> = coffees

    @GetMapping("/{id}")
    fun getCoffee(@PathVariable id: String): Coffee? {
        return coffees.firstOrNull { it.id == id }
    }

    @PostMapping
    fun postCoffee(@RequestBody coffee: Coffee): Coffee {
        coffees.add(coffee)
        return coffee
    }

    @PutMapping("/{id}")
    fun putCoffee(@PathVariable id: String, @RequestBody coffee: Coffee): ResponseEntity<Coffee> {
        val index = coffees.indexOfFirst { it.id == id }
        return if (index != -1) {
            coffees[index] = coffee
            ResponseEntity(coffee, HttpStatus.OK)
        } else ResponseEntity(postCoffee(coffee), HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteCoffee(@PathVariable id: String) {
        coffees.removeIf { it.id == id }
    }
}