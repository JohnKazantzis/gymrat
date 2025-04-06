package com.ik.gymrat.persistence.repository;

import com.ik.gymrat.persistence.entity.Workout;
import com.ik.gymrat.persistence.entity.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

    List<WorkoutExercise> findAllByWorkout(Workout workout);

}
