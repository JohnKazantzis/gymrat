package com.ik.gymrat.api.controller;

import com.ik.gymrat.persistence.entity.WorkoutSet;
import com.ik.gymrat.service.WorkoutSetService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/workoutsets")
public class WorkoutSetController {

    private final WorkoutSetService workoutSetService;

    public WorkoutSetController(WorkoutSetService workoutSetService) {
        this.workoutSetService = workoutSetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutSet> getWorkoutSetById(@PathVariable long id) {
        System.out.println("getWorkoutSetById 1");
        WorkoutSet workoutSet = this.workoutSetService.getWorkoutSetById(id);
        System.out.println("getWorkoutSetById 4: " + workoutSet);
        return ResponseEntity.ok(workoutSet);
    }

    @GetMapping("/workoutexercise/{id}")
    public ResponseEntity<List<WorkoutSet>> getWorkoutSetsByWorkoutSet(@PathVariable long id) {
        return ResponseEntity.ok(this.workoutSetService.getWorkoutSetsByWorkoutExercise(id));
    }

}
