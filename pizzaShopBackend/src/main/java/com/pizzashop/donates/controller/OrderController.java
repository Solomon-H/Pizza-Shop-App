package com.pizzashop.donates.controller;

import com.pizzashop.donates.model.Pizza;
import com.pizzashop.donates.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class OrderController {

    private final PizzaService pizzaService;

    @Autowired
    public OrderController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/pizzas")
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/pizza/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable Long id) {
        Optional<Pizza> pizza = pizzaService.getPizzaById(id);
        if (pizza.isPresent()) {
            return ResponseEntity.ok(pizza.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The pizza order not found");
        }
    }

    @GetMapping("/pizza/{id}/cost")
    public ResponseEntity<BigDecimal> pizzaTotalCost(@PathVariable Long id) {
        Optional<Pizza> pizza = pizzaService.getPizzaById(id);
        if (pizza.isPresent()) {
            BigDecimal totalCost = pizzaService.calculatePizzaPrice(pizza.get());
            return ResponseEntity.ok(totalCost);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The pizza order not found");
        }
    }

    @PostMapping("/add")
    public Pizza newPizza(@RequestBody Pizza newPizza) {
        BigDecimal pizzaPrice = pizzaService.calculatePizzaPrice(newPizza);
        newPizza.setPrice(pizzaPrice);

        Pizza createdPizza = pizzaService.addPizza(newPizza);
        return createdPizza;
}

    @PutMapping("/pizza/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable Long id, @RequestBody Pizza updatedPizza) {
        Pizza pizza = pizzaService.getPizzaById(id).orElse(null);
        if (pizza != null) {
        BigDecimal pizzaPrice = pizzaService.calculatePizzaPrice(updatedPizza);
        updatedPizza.setPrice(pizzaPrice);

        Pizza updatedOrder = pizzaService.updatePizza(id, updatedPizza);

        return ResponseEntity.ok(updatedOrder);
        }
        else
         {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The pizza order not found");
    }
}

    @DeleteMapping("/pizza/{id}")
    public ResponseEntity<Void> removePizza(@PathVariable Long id) {
        pizzaService.deletePizza(id);
        return ResponseEntity.noContent().build();
    }

}
