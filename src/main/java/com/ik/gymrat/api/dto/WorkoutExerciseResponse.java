package com.ik.gymrat.api.dto;

import com.ik.gymrat.persistence.entity.WorkoutExercise;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class WorkoutExerciseResponse {

    private long id;
    private String exerciseName;
    private List<WorkoutSetResponse> workoutSets;

    public WorkoutExerciseResponse(WorkoutExercise workoutExercise) {
        this.id = workoutExercise.getId();
        this.exerciseName = workoutExercise.getExercise().getName();
        this.workoutSets = workoutExercise.getWorkoutSets().stream().map((workoutSet) -> {
            return new WorkoutSetResponse(workoutSet);
        }).collect(Collectors.toList());
    }
}
