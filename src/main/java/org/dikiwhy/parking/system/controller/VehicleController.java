package org.dikiwhy.parking.system.controller;

import org.dikiwhy.parking.system.request.RegisterVehicleRequest;
import org.dikiwhy.parking.system.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterVehicleRequest request){
        return vehicleService.register(request);
    }

    @PostMapping("/exit")
    public ResponseEntity<String> exit(@RequestBody RegisterVehicleRequest request){
        return vehicleService.exit(request);
    }
}
