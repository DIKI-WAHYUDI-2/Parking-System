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
        vehicle.setNumberPlate("BM 1234 AA");
        vehicle.setTypeVehicle("Roda Dua");
        vehicle.setEntryTime(new Date());

        vehicleService.saveVehicle(vehicle);

        assertNotNull(vehicle.getNumberPlate());
        assertNotNull(vehicle.getTypeVehicle());
        assertNotNull(vehicle.getEntryTime());
    }

    @Test
    void paid(){

        String bm = "BM 1234 AA";

        Vehicle vehicle = repository.findById(bm).orElse(null);

        if(vehicle != null){
            vehicleService.paidVehicle(bm);

            assertNotNull(vehicle.getNumberPlate());
            assertNotNull(vehicle.getTypeVehicle());
            assertNotNull(vehicle.getEntryTime());
            assertNotNull(vehicle.getExitTime());
            assertNotNull(vehicle.getLongParkingTime());
            assertNotNull(vehicle.getParkingFee());
        }
    }

    @Test
    void query() {

        long time;
        Date timeDb;
        Date timeNow = new Date();
        String bm = "BM 1234 AA";

        Vehicle vehicle = repository.findById(bm).orElse(null);

        if (vehicle != null){

            timeDb = vehicle.getEntryTime();

            time = timeNow.getTime() - timeDb.getTime();

            long seconds = time / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;

            System.out.println("Selisih waktu dalam milisekon: " + time);
            System.out.println("Selisih waktu dalam detik: " + seconds);
            System.out.println("Selisih waktu dalam menit: " + minutes);
            System.out.println("Selisih waktu dalam jam: " + hours);
            System.out.println(time);
        }

    }

}