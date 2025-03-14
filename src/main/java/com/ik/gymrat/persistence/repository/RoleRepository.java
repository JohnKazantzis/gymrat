package com.ik.gymrat.persistence.repository;

import com.ik.gymrat.persistence.entity.ERole;
import com.ik.gymrat.persistence.entity.Role;
import com.ik.gymrat.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

    List<Role> findByNameIn(Set<ERole> names);
}
