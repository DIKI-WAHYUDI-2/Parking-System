package org.dikiwhy.parking.system.service;

import org.dikiwhy.parking.system.entity.Vehicle;
import org.dikiwhy.parking.system.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehicleServiceTest {

    @Autowired
    private VehicleRepository repository;

    @Autowired
    private VehicleService vehicleService;

    @Test
    void save() {

        Vehicle vehicle = new Vehicle();
        vehicle.setNumberPlate("BM 1990 AD");
        vehicle.setTypeVehicle("Roda Empat");
        vehicle.setEntryTime(new Date());
        vehicle.setStatus("belum bayar");

        vehicleService.saveVehicle(vehicle);

        assertNotNull(vehicle.getNumberPlate());
        assertNotNull(vehicle.getTypeVehicle());
        assertNotNull(vehicle.getEntryTime());
    }

    @Test
    void paid(){

        String bm = "BM 1990 AD";

        vehicleService.paidVehicle(bm);
    }

    private Long countLongParkingTime(String numberPlate) {

        Date timeStart,timeEnd;
        long minutes;

        Vehicle vehicle1 = repository.findById(numberPlate).orElse(null);

        if (vehicle1 != null) {
            timeStart = vehicle1.getEntryTime();
            timeEnd = vehicle1.getExitTime();

            long time = timeEnd.getTime() - timeStart.getTime();

            minutes = (time / 1000) / 60;

            return minutes;
        }

        return null;
    }


}