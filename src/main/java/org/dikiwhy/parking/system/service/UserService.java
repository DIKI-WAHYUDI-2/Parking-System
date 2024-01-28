package org.dikiwhy.parking.system.service;

import org.dikiwhy.parking.system.entity.Role;
import org.dikiwhy.parking.system.entity.User;
import org.dikiwhy.parking.system.request.RegisterUserRequest;
import org.dikiwhy.parking.system.repository.RoleRepository;
import org.dikiwhy.parking.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<String> register(RegisterUserRequest userRequest){
        if (userRepository.existsByUsername(userRequest.getUsername())){

            return new ResponseEntity<>("Username already registered!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    public ResponseEntity<User> getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String username = userDetails.getUsername();

            return ResponseEntity.ok(userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found")));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<String> update(RegisterUserRequest request){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return new ResponseEntity<>("User data updated successfully!", HttpStatus.OK);
    }

}
