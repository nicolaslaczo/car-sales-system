package com.app.demo.handler;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(Integer orderId) {
        super("Order with " + orderId + " not found");
    }
}
