package com.ik.gymrat.api.dto;

import com.ik.gymrat.persistence.entity.Workout;
import com.ik.gymrat.persistence.entity.WorkoutExercise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class WorkoutResponse {
    private long id;
    private Date workoutDate;
    private Set<String> muscleGroups = new HashSet<>();

    public WorkoutResponse(Workout workout) {
        this.id = workout.getId();
        this.workoutDate = workout.getWorkoutDate();
        workout.getWorkoutExercises().forEach(exercise -> {
            this.muscleGroups.add(exercise.getExercise().getMuscleGroup().getName());
        });
    }
}
