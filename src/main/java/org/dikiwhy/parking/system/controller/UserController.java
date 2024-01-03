package org.dikiwhy.parking.system.controller;

import org.dikiwhy.parking.system.entity.User;
import org.dikiwhy.parking.system.entity.Vehicle;
import org.dikiwhy.parking.system.repository.UserRepository;
import org.dikiwhy.parking.system.repository.VehicleRepository;
import org.dikiwhy.parking.system.service.UserService;
import org.dikiwhy.parking.system.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/users")
    public String user(){
        return "user";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String save(User user) {
        service.saveUser(user);
        return "redirect:/dashboard-data";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(User user, RedirectAttributes redirectAttributes) {
        boolean success = service.loginUser(user);
        if (!success) {
            redirectAttributes.addFlashAttribute("alert", "Invalid username and/or password");
            return "redirect:/login";
        }
        return "redirect:/dashboard-data";
    }

    @PostMapping("/dashboard-data")
    public String dashboard(Model model){

        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);

        return "dashboard";
    }

//    @Controller
//    public class HomeController {
//        @GetMapping("/")
//        public String greeting() {
//            return "index";
//        }
//
//        @GetMapping("/login")
//        public String login() {
//            return "login";
//        }
//
//        @GetMapping("/admin")
//        public String admin() {
//            return "admin";
//        }
//
//        @GetMapping("/manager")
//        public String manager() {
//            return "manager";
//        }
//
//        @GetMapping("/employee")
//        public String employee() {
//            return "employee";
//        }
//
//        @GetMapping("/logout")
//        public String logout() {
//            return "redirect:/login";
//        }
//    }


}
