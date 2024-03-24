package com.app.demo.controllers;

import com.app.demo.entity.Part;
import com.app.demo.entity.Vehicle;
import com.app.demo.services.PartService;
import com.app.demo.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceOrder {
    @Autowired
    private PartService partService;
    @Autowired
    private VehicleService vehicleService;


    // ----------- Parts controllers -------------- //

    @GetMapping("/service/parts")
    public ResponseEntity<List<Part>> getAllParts() {

        return new ResponseEntity<>(partService.getAllPartsInStorage(), HttpStatus.OK);
    }

    @GetMapping("/service/parts/{partNum}")
    public ResponseEntity<Part> getPartByPartNum(@PathVariable String partNum) {

        return new ResponseEntity<>(partService.getPartByPartNum(partNum),HttpStatus.OK);
    }

    @PostMapping("/service/parts")
    public ResponseEntity<HttpStatus> addPartToStorage(@RequestBody Part part) {
        partService.addPartToStorage(part);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/service/parts/price/{partNum}")
    public ResponseEntity<HttpStatus> updatePartPrice(@PathVariable String partNum,@RequestBody Part part) {
        partService.updatePartPrice(partNum,part);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/service/parts/{partNum}")
    public ResponseEntity<HttpStatus> deletePart(@PathVariable String partNum) {
        partService.deletePartByPartNum(partNum);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // ---------- Vehicle controllers ----------//

    @GetMapping("/service/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {

        return new ResponseEntity<>(vehicleService.getAllVehicles(),HttpStatus.OK);
    }

    @GetMapping("/service/vehicles/{plateNum}")
    public ResponseEntity<Vehicle> getVehicleByPlateNum(@PathVariable String plateNum) {

        return new ResponseEntity<>(vehicleService.getVehicleByPlateNum(plateNum),HttpStatus.OK);
    }

    @PostMapping("/service/vehicles")
    public ResponseEntity<HttpStatus> addNewVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicleToStorage(vehicle);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/service/vehicles/{plateNum}")
    public ResponseEntity<HttpStatus> updateVehicleParameters(@PathVariable String plateNum,@RequestBody Vehicle vehicle) {
        vehicleService.updateVehicleParameters(plateNum,vehicle);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/service/vehicles/{plateNum}")
    public ResponseEntity<HttpStatus> deleteVehicleByPlateNum(@PathVariable String plateNum) {
        vehicleService.deleteVehicleByPlateNum(plateNum);

        return new ResponseEntity<>(HttpStatus.OK);
    }





























}
