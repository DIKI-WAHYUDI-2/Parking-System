package org.dikiwhy.parking.system.service;

import org.dikiwhy.parking.system.entity.User;
import org.dikiwhy.parking.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    public void saveUser(User user){
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());

        repository.save(newUser);
    }

    public boolean loginUser(User user){

        Optional<User> userExist = repository.findById(user.getEmail());

        var cekUsername = userExist.equals(repository.findById(user.getEmail()));
        var cekPassword = userExist.get().getPassword().equals(user.getPassword());

        if (cekUsername && cekPassword){
            return true;
        }
        return false;
    }


}
