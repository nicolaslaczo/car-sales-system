package com.app.demo.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderHasParts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part parts;

    private int quantity;


    public Integer calculatePartCost() {
        int partPrice = Integer.parseInt(parts.getPartPrice());
        return partPrice * quantity;
    }

    public void increaseQuantity() {
        quantity = quantity +1;
    }









































}
