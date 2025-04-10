package com.ik.gymrat.api.controller;

import com.ik.gymrat.api.dto.WorkoutExerciseResponse;
import com.ik.gymrat.service.WorkoutExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/api/workoutexercises")
public class WorkoutExerciseController {

    private final WorkoutExerciseService workoutExerciseService;

    public WorkoutExerciseController(WorkoutExerciseService workoutExerciseService) {
        this.workoutExerciseService = workoutExerciseService;
    }

    @GetMapping()
    public ResponseEntity<List<WorkoutExerciseResponse>> getWorkoutExercisesByWorkout(@RequestParam long workoutId) {
        List<WorkoutExerciseResponse> workoutExerciseResponses = this.workoutExerciseService.getWorkoutExercisesByWorkout(workoutId);
        return ResponseEntity.ok(workoutExerciseResponses);
    }
}
