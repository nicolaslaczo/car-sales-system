package com.app.demo.services;

import com.app.demo.entity.Orders;
import com.app.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void createServiceOrder(Orders orders) {
        orderRepository.save(orders);
    }










































}
