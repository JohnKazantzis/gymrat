package com.ik.gymrat.persistence.repository;

import com.ik.gymrat.persistence.entity.ERole;
import com.ik.gymrat.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
