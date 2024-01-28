package org.dikiwhy.parking.system.controller;

import org.dikiwhy.parking.system.entity.User;
import org.dikiwhy.parking.system.request.RegisterUserRequest;
import org.dikiwhy.parking.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserRequest request){
        return userService.register(request);
    }

    @GetMapping("/current")
    public ResponseEntity<User> getUserInfo() {
        return userService.getUserInfo();
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody RegisterUserRequest request) {
        return userService.update(request);
    }
}
