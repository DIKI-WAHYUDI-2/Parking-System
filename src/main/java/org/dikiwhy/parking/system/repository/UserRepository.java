package org.dikiwhy.parking.system.repository;

import org.dikiwhy.parking.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

}
