package com.ik.gymrat.api.controller;

import com.ik.gymrat.exceptions.MuscleGroupNotFoundException;
import com.ik.gymrat.persistence.entity.MuscleGroup;
import com.ik.gymrat.service.MuscleGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/musclegroups")
public class MuscleGroupController {

    private final MuscleGroupService muscleGroupService;

    public MuscleGroupController(MuscleGroupService muscleGroupService) {
        this.muscleGroupService = muscleGroupService;
    }

    @GetMapping
    public ResponseEntity<List<MuscleGroup>> getAllMuscleGroups() {
        return ResponseEntity.ok(this.muscleGroupService.getAllMuscleGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MuscleGroup> getMuscleGroupById(@PathVariable long id) {
        return ResponseEntity.ok(this.muscleGroupService.getMuscleGroupById(id));
    }

    @ExceptionHandler(MuscleGroupNotFoundException.class)
    public ResponseEntity<Void> handleMuscleGroupNotFoundException(MuscleGroupNotFoundException e) {
        System.out.println("Muscle group not found");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleException(Exception e){
        System.out.println("An error occured: " + e.getMessage());
        return ResponseEntity.internalServerError().build();
    }


}
