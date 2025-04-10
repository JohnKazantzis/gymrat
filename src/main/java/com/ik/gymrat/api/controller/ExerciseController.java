package com.ik.gymrat.api.controller;

import com.ik.gymrat.api.dto.ExerciseResponse;
import com.ik.gymrat.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping()
    public ResponseEntity<List<ExerciseResponse>> getExercisesByMuscleGroup(@RequestParam long muscleGroupId) {
        return ResponseEntity.ok(this.exerciseService.getExercisesByMuscleGroup(muscleGroupId));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleGenericException(Exception e) {
        System.out.println("An error occured: " + e.getMessage());
        return ResponseEntity.internalServerError().build();
    }
}
