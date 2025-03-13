package com.ik.gymrat.persistence.repository;

import com.ik.gymrat.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String name);
    
    Boolean existsByEmail(String email);
}
