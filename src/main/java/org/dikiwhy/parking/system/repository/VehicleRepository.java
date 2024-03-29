package org.dikiwhy.parking.system.repository;

import org.dikiwhy.parking.system.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    Optional<Vehicle>findByPoliceNumber(String policeNumber);
    String findByVehicleType(String vehicleType);
}
