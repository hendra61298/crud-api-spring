package com.film.moviecrudapi.database.repository;

import com.film.moviecrudapi.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findById(String id);

    User findByName(String name);
}
