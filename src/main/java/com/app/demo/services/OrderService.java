package com.app.demo.services;

import com.app.demo.entity.*;
import com.app.demo.handler.*;
import com.app.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    OrderHasPartsRepository orderHasPartsRepository;


    public void createServiceOrder(Orders orders) {
        orderRepository.save(orders);
    }

    public void deleteServiceOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }



    public void addNewCustomerToOrder(Integer orderId, Customer newCustomer) {
        Customer savedCustomer = customerRepository.save(newCustomer);
        Orders existingOrder =orderRepository.findById(orderId).orElseThrow();
        existingOrder.setCustomer(savedCustomer);
        orderRepository.save(existingOrder);
    }

    public Customer getCustomerFromOrder(Integer orderId) {
        Orders existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        return existingOrder.getCustomer();

    }


    public void addCustomerToOrderFromDb(Integer orderId,Integer customerId) {
        Orders existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        existingOrder.setCustomer(existingCustomer);
        orderRepository.save(existingOrder);
    }


    public void deleteCustomerFromOrderById(Integer orderId,Integer customerId) {
        Orders existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        Customer existingCustomer = existingOrder.getCustomer();
        if (existingCustomer.getId().equals(customerId)) {
            existingOrder.setCustomer(null);
        } else {
            throw new CustomerNotFoundException(customerId);
        }
    }


    // ------------------------------- Vehicle to order -------------------//

    public void addNewVehicleToOrder(Integer orderId, Vehicle vehicle) {
        Orders existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (existingOrder.getVehicle() != null) {
            existingOrder.setVehicle(vehicle);
        } else {
            throw new VehicleFoundException(vehicle.getId());
        }
    }

    public void addVehicleFromDb(Integer orderId,Integer vehicleId) {
        Orders existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        Vehicle existingVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(vehicleId));

        existingOrder.setVehicle(existingVehicle);
        orderRepository.save(existingOrder);
    }


    // ------------------------ Parts to order  ------------------------- //

    public void addPartToOrderFromDb(Integer orderId,String partNum) {
        Orders existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        Part existingPart = partRepository.findByPartNum(partNum);
        if (existingPart != null){
            OrderHasParts orderHasParts = new OrderHasParts();
            orderHasParts.setOrders(existingOrder);
            orderHasParts.setParts(existingPart);
            orderHasPartsRepository.save(orderHasParts);

        } else {
            throw new PartNotFoundException(partNum);
        }

    }

    public List<Part> getPartsFromOrder(Integer orderId) {
        return orderHasPartsRepository.findByOrdersId(orderId);

    }













































}
