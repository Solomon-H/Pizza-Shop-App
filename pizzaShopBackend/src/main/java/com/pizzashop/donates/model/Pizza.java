package com.pizzashop.donates.model;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_id")
    private Long pizzaId;
    private String sauce;
    @Enumerated(EnumType.STRING)
    private PizzaSize size;
    private String topping;
    private BigDecimal price;

}
