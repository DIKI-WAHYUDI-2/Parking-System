package org.dikiwhy.parking.system.controller;

import org.dikiwhy.parking.system.entity.Vehicle;
import org.dikiwhy.parking.system.repository.VehicleRepository;
import org.dikiwhy.parking.system.service.VehicleService;
import org.dikiwhy.parking.system.util.SearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @Autowired
    private VehicleRepository repo;

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

//    @GetMapping("/paid/{numberPlate}")
//    public String paid(@PathVariable String numberPlate, Model model){
//        Vehicle vehicle = repo.findById(numberPlate).orElse(null);
//        model.addAttribute("vehicle", vehicle);
//        return "";
//    }

    @GetMapping("/paid")
    public String paid(Model model){
        model.addAttribute("vehicle", new Vehicle());
        return "paid";
    }

    @PostMapping("/paid")
    public String paidProcess(Vehicle vehicle){
        service.paidVehicle(vehicle.getNumberPlate());

        return "redirect:/dashboard-data";
    }

    @GetMapping("/dashboard-data")
    public String dashboard(Model model){

        List<Vehicle> vehicles = service.findAll();
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("searchForm", new SearchData());

        return "dashboard";
    }

    @PostMapping("/search")
    public String search(SearchData searchData, Model model){

        model.addAttribute("searchForm", searchData);
        model.addAttribute("vehicles", service.findByNumberPlate(searchData.getKeyword()));
        return "dashboard";
    }

    @GetMapping("/edit/{numberPlate}")
    public String edit(@PathVariable String numberPlate, Model model){
        Vehicle vehicle = repo.findById(numberPlate).orElse(null);
        model.addAttribute("vehicle", vehicle);
        return "edit";
    }

    @PostMapping("/update")
    public String update(Vehicle vehicle){
        repo.save(vehicle);
        return "redirect:/dashboard-data";
    }
}
