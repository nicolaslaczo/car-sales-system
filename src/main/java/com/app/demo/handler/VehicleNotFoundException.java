package com.app.demo.handler;

public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException(String partNum) {
        super("Vehicle with " + partNum + "not found in database");
    }

    public VehicleNotFoundException(Integer vehicleId) {
        super("Vehicle with id " + vehicleId + " not found");
    }


}
