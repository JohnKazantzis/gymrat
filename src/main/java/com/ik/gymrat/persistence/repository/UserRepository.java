package com.ik.gymrat.persistence.repository;

import com.ik.gymrat.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
