package org.dikiwhy.parking.system.service;

import org.dikiwhy.parking.system.entity.Payment;
import org.dikiwhy.parking.system.entity.Ticket;
import org.dikiwhy.parking.system.entity.Vehicle;
import org.dikiwhy.parking.system.request.RegisterVehicleRequest;
import org.dikiwhy.parking.system.repository.PaymentRepository;
import org.dikiwhy.parking.system.repository.TicketRepository;
import org.dikiwhy.parking.system.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public ResponseEntity<String> register(RegisterVehicleRequest request){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {

            Vehicle vehicle = new Vehicle();
            vehicle.setPoliceNumber(request.getPoliceNumber());
            vehicle.setVehicleType(request.getVehicleType());

            Payment payment = new Payment();
            payment.setEntryTime(new Date());

            Ticket ticket = new Ticket();
            ticket.setVehicle(vehicle);
            ticket.setPayment(payment);

            vehicleRepository.save(vehicle);
            paymentRepository.save(payment);
            ticketRepository.save(ticket);

            return new ResponseEntity<>("Vehicle registered success!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<String> exit(RegisterVehicleRequest request){

            Vehicle vehicle = vehicleRepository.findByPoliceNumber(request.getPoliceNumber()).orElseThrow();
            Payment payment = paymentRepository.findById(vehicle.getId()).orElseThrow();
            payment.setLongParkingTime(countLongParkingTime(request));
            payment.setTotalPayment(countParkingFee(request));
            paymentRepository.save(payment);

            return new ResponseEntity<>("Lama parkir : " + payment.getLongParkingTime() + "\n Total bayar : " + payment.getTotalPayment(), HttpStatus.OK);
    }

    private Long countLongParkingTime(RegisterVehicleRequest request) {
        Vehicle vehicle = vehicleRepository.findByPoliceNumber(request.getPoliceNumber()).orElseThrow();
        Payment payment = paymentRepository.findById(vehicle.getId()).orElseThrow();
        payment.setExitTime(new Date());

        var exit = payment.getExitTime();
        var enter = payment.getEntryTime();

        var total = exit.getTime() - enter.getTime();
        var minutes = Math.max(0, total - 60000); // Pastikan nilai menit tidak negatif
        return minutes;
    }

    private Integer countParkingFee(RegisterVehicleRequest request) {
        Integer fee = 0;
        Long time = countLongParkingTime(request);

        // Menggunakan properti vehicleType dari entitas Vehicle
        String vehicleType = vehicleRepository.findByPoliceNumber(request.getPoliceNumber())
                .orElseThrow().getVehicleType();

        if (vehicleType.equalsIgnoreCase("RODA DUA")) {
            fee = 2000;

            if (time >= 120) {
                fee += 1000;
            } else if (time >= 240) {
                fee += 2000;
            } else if (time >= 360) {
                fee += 3000;
            } else if (time >= 480) {
                fee = 6000;
            }
        } else if (vehicleType.equalsIgnoreCase("RODA EMPAT")) {
            fee = 3000;

            if (time >= 120) {
                fee += 1000;
            } else if (time >= 240) {
                fee += 2000;
            } else if (time >= 360) {
                fee += 3000;
            } else if (time >= 480) {
                fee = 6000;
            }
        }
        return fee;
    }
}
