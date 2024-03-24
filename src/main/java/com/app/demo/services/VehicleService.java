package com.app.demo.services;

import com.app.demo.entity.Vehicle;
import com.app.demo.handler.VehicleFoundException;
import com.app.demo.handler.VehicleNotFoundException;
import com.app.demo.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;


    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleByPlateNum(String plateNum) {
        Vehicle existingVehicle = vehicleRepository.findByPlateNum(plateNum);
        if (existingVehicle != null) {
            return existingVehicle;
        } else {
            throw new VehicleNotFoundException(plateNum);
        }
    }

    public void addVehicleToStorage(Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findByPlateNum(vehicle.getPlateNum());
        if (existingVehicle == null) {
            vehicleRepository.save(vehicle);
        } else {
            throw new VehicleFoundException(vehicle.getPlateNum());
        }
    }

    public void updateVehicleParameters(String plateNum,Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findByPlateNum(plateNum);
        if (existingVehicle != null) {
            existingVehicle.setBrand(vehicle.getBrand());
            existingVehicle.setModel(vehicle.getModel());
            existingVehicle.setYear(vehicle.getYear());
            vehicleRepository.save(existingVehicle);
        } else {
            throw new VehicleFoundException(plateNum);
        }
    }


    public void deleteVehicleByPlateNum(String plateNum) {
        vehicleRepository.deleteByPlateNum(plateNum);
    }



















}
