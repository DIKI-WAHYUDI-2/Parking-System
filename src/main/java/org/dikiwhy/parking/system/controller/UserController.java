package org.dikiwhy.parking.system.controller;

import org.dikiwhy.parking.system.entity.User;
import org.dikiwhy.parking.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/login")
    public String save(User user) {
        service.saveUser(user);
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/dashboard")
    public String dashboard(User user, RedirectAttributes redirectAttributes) {
        boolean success = service.loginUser(user);
        if (!success) {
            redirectAttributes.addFlashAttribute("alert", "Gagal login");
            return "redirect:/login";
        }
        return "dashboard";
    }

}
