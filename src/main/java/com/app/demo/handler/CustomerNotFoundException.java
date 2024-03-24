package com.app.demo.handler;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Integer customerId) {
        super("Customer with " + customerId + " not exist");
    }
}
