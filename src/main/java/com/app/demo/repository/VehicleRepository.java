package com.app.demo.repository;

import com.app.demo.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

    Vehicle findByPlateNum(String plateNum);
    public void deleteByPlateNum(String plateNum);
}
