package org.dikiwhy.parking.system.repository;

import org.dikiwhy.parking.system.entity.Payment;
import org.dikiwhy.parking.system.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<Payment>findById(Vehicle vehicle);
}
