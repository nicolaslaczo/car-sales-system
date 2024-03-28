package com.app.demo.repository;

import com.app.demo.entity.Customer;
import com.app.demo.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {


}
