package com.app.demo.controllers;

import com.app.demo.entity.Customer;
import com.app.demo.entity.Orders;
import com.app.demo.entity.Part;
import com.app.demo.entity.Vehicle;
import com.app.demo.services.CustomerService;
import com.app.demo.services.OrderService;
import com.app.demo.services.PartService;
import com.app.demo.services.VehicleService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceOrder {
    @Autowired
    private PartService partService;
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;




                // ----------- Parts controllers -------------- //

                    // - Get all parts in storage - //

    @GetMapping("/service/parts")
    public ResponseEntity<List<Part>> getAllParts() {

        return new ResponseEntity<>(partService.getAllPartsInStorage(), HttpStatus.OK);
    }

                    // - Get one part from storage - //
    @GetMapping("/service/parts/{partNum}")
    public ResponseEntity<Part> getPartByPartNum(@PathVariable String partNum) {

        return new ResponseEntity<>(partService.getPartByPartNum(partNum),HttpStatus.OK);
    }
                     // - Add new part to storage - //
    @PostMapping("/service/parts")
    public ResponseEntity<HttpStatus> addPartToStorage(@RequestBody Part part) {
        partService.addPartToStorage(part);
        return new ResponseEntity<>(HttpStatus.OK);
    }
                    // - Update part price - //
    @PutMapping("/service/parts/price/{partNum}")
    public ResponseEntity<HttpStatus> updatePartPrice(@PathVariable String partNum,@RequestBody Part part) {
        partService.updatePartPrice(partNum,part);

        return new ResponseEntity<>(HttpStatus.OK);
    }
                    // - Delete part from storage - //
    @DeleteMapping("/service/parts/{partNum}")
    public ResponseEntity<HttpStatus> deletePart(@PathVariable String partNum) {
        partService.deletePartByPartNum(partNum);

        return new ResponseEntity<>(HttpStatus.OK);
    }

                // ---------- Vehicle controllers ----------//
                    // - Get all vehicles from db - //
    @GetMapping("/service/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {

        return new ResponseEntity<>(vehicleService.getAllVehicles(),HttpStatus.OK);
    }
                    // - Get vehicle by part num - //
    @GetMapping("/service/vehicles/{plateNum}")
    public ResponseEntity<Vehicle> getVehicleByPlateNum(@PathVariable String plateNum) {

        return new ResponseEntity<>(vehicleService.getVehicleByPlateNum(plateNum),HttpStatus.OK);
    }
                    // - Add new vehicle - //
    @PostMapping("/service/vehicles")
    public ResponseEntity<HttpStatus> addNewVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicleToStorage(vehicle);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
                    // - Update vehicle by plate - //
    @PutMapping("/service/vehicles/{plateNum}")
    public ResponseEntity<HttpStatus> updateVehicleParameters(@PathVariable String plateNum,@RequestBody Vehicle vehicle) {
        vehicleService.updateVehicleParameters(plateNum,vehicle);

        return new ResponseEntity<>(HttpStatus.OK);
    }
                    // - Delete vehicle by plateNum - //
    @DeleteMapping("/service/vehicles/{plateNum}")
    public ResponseEntity<HttpStatus> deleteVehicleByPlateNum(@PathVariable String plateNum) {
        vehicleService.deleteVehicleByPlateNum(plateNum);

        return new ResponseEntity<>(HttpStatus.OK);
    }

                // ------------- Customer controllers ------------- //
                // - Add new customer - //
    @PostMapping("/service/customers")
    public ResponseEntity<HttpStatus> addNewCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);

        return new ResponseEntity<>(HttpStatus.OK);
    }
                // - Get all customers - //
    @GetMapping("/service/customers")
    public ResponseEntity<List<Customer>>getAllCustomers() {

        return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
    }
                // - Delete one customer - //
    @DeleteMapping("/service/customers/{customerId}")
    public ResponseEntity<HttpStatus> deleteCustomerById(@PathVariable Integer customerId) {
        customerService.deleteCustomerById(customerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
                // - Update customer by id - //
    @PutMapping("/service/customers/{customerId}")
    public ResponseEntity<HttpStatus> updateCustomerParameters(@PathVariable Integer customerId,@RequestBody Customer customer) {
        customerService.updateCustomerParameters(customerId,customer);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // ----------------------- Order operations ------------------ //
                     // - Create new service order - //

    @PostMapping("/service/serviceOrder")
    public ResponseEntity<HttpStatus> createServiceOrder(@RequestBody Orders orders) {
        orderService.createServiceOrder(orders);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

                // - Delete service order - //

    @DeleteMapping("/service/serviceOrder/{orderId}")
    public ResponseEntity<HttpStatus> deleteServiceOrder(@PathVariable Integer orderId) {
        orderService.deleteServiceOrder(orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


                    // - Add customer to order - //
    @PostMapping("/service/serviceOrder/{orderId}/addCustomer")
    public ResponseEntity<HttpStatus> addNewCustomerToOrder(@PathVariable Integer orderId,@RequestBody Customer newCustomer) {
        orderService.addNewCustomerToOrder(orderId,newCustomer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
                // - Get customer from order - //
    @GetMapping("/service/serviceOrder/{orderId}/getCustomer")
    public ResponseEntity<Customer> getCustomerFromOrder(@PathVariable Integer orderId) {

        return ResponseEntity.ok(orderService.getCustomerFromOrder(orderId));
    }

            // - Add customer to order from db - //
    @PostMapping("/service/order/{orderId}/addCustomerFromDb/{customerId}")
    public ResponseEntity<HttpStatus> addCustomerFromDb(@PathVariable Integer orderId,@PathVariable Integer customerId) {
        orderService.addCustomerToOrderFromDb(orderId,customerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

            // - Delete customer from order by id - //

    @PostMapping("/service/order/{orderId}/deleteCustomerFromOrder/{customerId}")
    public ResponseEntity<HttpStatus> deleteCustomerFromOrder(@PathVariable Integer orderId,@PathVariable Integer customerId) {
        orderService.deleteCustomerFromOrderById(orderId,customerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


            // - Add new vehicle to order - //

    @PostMapping("/service/order/{orderId}/addVehicle")
    public ResponseEntity<HttpStatus>addVehicleToOrder(@PathVariable Integer orderId,@RequestBody Vehicle vehicle) {
        orderService.addNewVehicleToOrder(orderId,vehicle);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

            // - Add vehicle to order from DB - //

    @PostMapping("/service/order/{orderId}/addVehicle/{vehicleId}")
    public ResponseEntity<HttpStatus> addVehicleFromDb(@PathVariable Integer orderId,@PathVariable Integer vehicleId) {
        orderService.addVehicleFromDb(orderId,vehicleId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


        // - Add part to order from storage - //

    @PostMapping("/service/order/{orderId}/addPartFromDb/{partNum}")
    public ResponseEntity<HttpStatus>addPartFromDb(@PathVariable Integer orderId,@PathVariable String partNum) {
        orderService.addPartToOrderFromDb(orderId,partNum);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/service/order/{orderId}/getParts")
    public ResponseEntity<List<Part>> getPartsFromOrder(@PathVariable Integer orderId) {

        return ResponseEntity.ok(orderService.getPartsFromOrder(orderId));
    }





































}
