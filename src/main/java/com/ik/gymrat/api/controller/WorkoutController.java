package com.ik.gymrat.api.controller;

import com.ik.gymrat.api.dto.WorkoutResponse;
import com.ik.gymrat.service.WorkoutService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public ResponseEntity<Page<WorkoutResponse>> getAllWorkoutsByUser(
        @RequestParam int page,
        @RequestParam int size,
        @RequestParam long userId
    ) {
        Page<WorkoutResponse> workoutResponses = this.workoutService.getAllWorkouts(page, size, userId);
        return ResponseEntity.ok(workoutResponses);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleGenericException(Exception e) {
        return ResponseEntity.internalServerError().build();
    }

}
