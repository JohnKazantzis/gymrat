package com.ik.gymrat.persistence.repository;

import com.ik.gymrat.persistence.entity.Workout;
import com.ik.gymrat.persistence.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    Page<Workout> findAllByUser(User user, Pageable pageable);

}
