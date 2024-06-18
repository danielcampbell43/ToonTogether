package com.makersacademy.toon_together.repository;

import com.makersacademy.toon_together.model.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthoritiesRepository extends CrudRepository<Authority, Long> {
}
