package com.pizzashop.donates.repository;
import com.pizzashop.donates.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PizzaOrderRepository extends JpaRepository<Pizza, Long> {
    Optional<Pizza> findByPizzaId(Long pizzaId);
}