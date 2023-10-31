package org.dikiwhy.parking.system.controller;

import org.dikiwhy.parking.system.entity.Vehicle;
import org.dikiwhy.parking.system.service.VehicleService;
import org.dikiwhy.parking.system.util.SearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping("/add-vehicle")
    public String addVehicle(Model model){
        model.addAttribute("vehicle", new Vehicle());
        return "add";
    }

    @PostMapping("/dashboard")
    public String saveVehicle(Vehicle vehicle){

        service.saveVehicle(vehicle);
        return "redirect:/dashboard-data";
    }

    @GetMapping("/dashboard-data")
    public String dashboard(Model model){

        List<Vehicle> vehicles = service.findAll();
        model.addAttribute("vehicles", vehicles);

        return "dashboard";
    }

    @PostMapping("/search")
    public String search(SearchData numberPlate, Model model){

        model.addAttribute("numberPlate", numberPlate);
        model.addAttribute("vehicles", service.findByNumberPlate(numberPlate.getNumberplate()));
        return "dashboard";
    }
}
