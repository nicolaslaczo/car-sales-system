package com.app.demo.services;

import com.app.demo.entity.Customer;
import com.app.demo.handler.CustomerNotFoundException;
import com.app.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    public void addNewCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomerById(Integer customerId) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);
        if (existingCustomer.isPresent()) {
            customerRepository.deleteById(customerId);
        } else {
            throw new CustomerNotFoundException(customerId);
        }

    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void updateCustomerParameters(Integer customerId,Customer customer) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(customerId);
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            customerRepository.save(existingCustomer);
        } else {
            throw new CustomerNotFoundException(customerId);
        }
    }














}
