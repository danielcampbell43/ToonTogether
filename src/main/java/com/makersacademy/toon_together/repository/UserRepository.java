package com.makersacademy.toon_together.repository;

import com.makersacademy.toon_together.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);
    User getById(Integer id);
}
