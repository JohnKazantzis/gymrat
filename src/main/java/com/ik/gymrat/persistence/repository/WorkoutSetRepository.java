package com.ik.gymrat.persistence.repository;

import com.ik.gymrat.persistence.entity.WorkoutExercise;
import com.ik.gymrat.persistence.entity.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Long> {

    public List<WorkoutSet> findByWorkoutExercise(WorkoutExercise workoutExercise);

}
