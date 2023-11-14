//package org.dikiwhy.parking.system.service;
//
//import org.dikiwhy.parking.system.entity.User;
//import org.dikiwhy.parking.system.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class UserDetail implements UserDetailsService {
//
////    @Autowired
////    private UserRepository repository;
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        User user = repository.findByUsernameOrEmail(username, username);
////
////        if (user == null){
////            throw new UsernameNotFoundException("User not exist by username");
////        }
////        Set<GrantedAuthority> authorities = user.getRoles().stream()
////                .map((role) -> new SimpleGrantedAuthority(role.getName()))
////                .collect(Collectors.toSet());
////
////        return new  org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
////    }
//}
