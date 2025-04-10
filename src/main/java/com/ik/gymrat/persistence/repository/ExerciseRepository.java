package com.ik.gymrat.persistence.repository;

import com.ik.gymrat.persistence.entity.Exercise;
import com.ik.gymrat.persistence.entity.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByMuscleGroup(MuscleGroup muscleGroup);

}
