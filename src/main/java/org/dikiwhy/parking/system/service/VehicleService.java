package org.dikiwhy.parking.system.service;

import org.dikiwhy.parking.system.entity.Vehicle;
import org.dikiwhy.parking.system.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public List<Vehicle> findAll(){
        return repository.findAll();
    }

    public List<Vehicle> findByNumberPlate(String numberPlate){
        return repository.findByNumberPlateContains(numberPlate);
    }

    public void saveVehicle(Vehicle vehicle) {
        Vehicle newVehicle = new Vehicle();
        newVehicle.setNumberPlate(vehicle.getNumberPlate());
        newVehicle.setTypeVehicle(vehicle.getTypeVehicle());
        newVehicle.setEntryTime(new Date());

        repository.save(newVehicle);
    }

    public void paidVehicle(String numberPlate) {

        Vehicle vehicle = repository.findById(numberPlate).orElse(null);

        if (vehicle != null) {

            vehicle.setExitTime(new Date());

            repository.save(vehicle);

            vehicle.setLongParkingTime(countLongParkingTime(numberPlate));
            vehicle.setParkingFee(countParkingFee(numberPlate));
            repository.save(vehicle);

        }
    }

    private Long countLongParkingTime(String numberPlate) {

        Date timeStart, timeEnd;
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

    private Integer countParkingFee(String numberPlate){

        Vehicle vehicle = repository.findById(numberPlate).orElse(null);

        if (vehicle != null){

            int fee = 0;

            String typeVehicle = vehicle.getTypeVehicle();
            long minutes = vehicle.getLongParkingTime();

            if (typeVehicle.equals("Roda Dua")){
                fee = 2000;
            } else if (typeVehicle.equals("Roda Empat")) {
                fee = 3000;
            }
            return fee;
        }
        return null;
    }

//    private Integer cekMinutes(long minutes){
//
//        if (minutes > 240){
//
//        }
//    }
//
//    private Integer checkTypeVehicle(String typeVehicle){
//
//        if (typeVehicle.equals("Roda dua")){
//            return 2000;
//        }
//        return 3000;
//    }

}