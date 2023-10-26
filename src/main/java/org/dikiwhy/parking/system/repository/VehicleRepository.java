package org.dikiwhy.parking.system.repository;

import org.dikiwhy.parking.system.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

}
