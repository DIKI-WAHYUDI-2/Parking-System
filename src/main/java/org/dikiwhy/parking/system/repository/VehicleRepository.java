package org.dikiwhy.parking.system.repository;

import org.dikiwhy.parking.system.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    List<Vehicle> findByNumberPlateContains(String numberPlate);

}
