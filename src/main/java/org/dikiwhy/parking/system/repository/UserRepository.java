package org.dikiwhy.parking.system.repository;

import org.dikiwhy.parking.system.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
