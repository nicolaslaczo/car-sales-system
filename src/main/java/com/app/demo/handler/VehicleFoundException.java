package com.app.demo.handler;

public class VehicleFoundException extends RuntimeException{

    public VehicleFoundException(String plateNum) {
        super("Vehicle with " + plateNum + "exist in database");
    }

    public VehicleFoundException(Integer vehicleId) {
        super("Vehicle with" + vehicleId + " exist in order");
    }
}
