package com.pizzashop.donates.service;

import com.pizzashop.donates.model.Pizza;
import com.pizzashop.donates.repository.PizzaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaOrderRepository pizzaRepository;

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> getPizzaById(Long id) {
        return pizzaRepository.findById(id);
    }

    public Pizza addPizza(Pizza newPizza) {
        return pizzaRepository.save(newPizza);
    }

    public Pizza updatePizza(Long id, Pizza updatedPizza) {
        Optional<Pizza> existingPizzaOptional = pizzaRepository.findById(id);
        if (existingPizzaOptional.isPresent()) {
            Pizza existingPizza = existingPizzaOptional.get();
            existingPizza.setSauce(updatedPizza.getSauce());
            existingPizza.setSize(updatedPizza.getSize());
            existingPizza.setTopping(updatedPizza.getTopping());
            return pizzaRepository.save(existingPizza);
        } else {
            return null;
        }
    }

    public BigDecimal calculatePizzaPrice(Pizza pizza) {
        switch (pizza.getSize()) {
            case SMALL:
                return BigDecimal.valueOf(8.99);
            case MEDIUM:
                return BigDecimal.valueOf(10.99);
            case LARGE:
                return BigDecimal.valueOf(12.99);
            default:
                return BigDecimal.ZERO;  
        }
    }

    public boolean deletePizza(Long id) {
        pizzaRepository.deleteById(id);
        return true;
    }
}