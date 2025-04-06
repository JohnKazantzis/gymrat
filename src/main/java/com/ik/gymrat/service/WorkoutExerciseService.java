package com.ik.gymrat.service;

import com.ik.gymrat.api.dto.WorkoutExerciseResponse;
import com.ik.gymrat.persistence.entity.Workout;
import com.ik.gymrat.persistence.entity.WorkoutExercise;
import com.ik.gymrat.persistence.repository.WorkoutExerciseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutExerciseService {

    private final WorkoutExerciseRepository workoutExerciseRepository;

    public WorkoutExerciseService(WorkoutExerciseRepository workoutExerciseRepository) {
        this.workoutExerciseRepository = workoutExerciseRepository;
    }

    public List<WorkoutExerciseResponse> getWorkoutExercisesByWorkout(long workoutId) {
        Workout workout = new Workout(workoutId);
        List<WorkoutExercise> workoutExercises = this.workoutExerciseRepository.findAllByWorkout(workout);
        List<WorkoutExerciseResponse> workoutExerciseResponses = workoutExercises.stream().map(
                workoutExercise -> new WorkoutExerciseResponse(workoutExercise)
        ).collect(Collectors.toList());
        return workoutExerciseResponses;
    }
}
