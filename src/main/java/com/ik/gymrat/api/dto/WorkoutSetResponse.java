package com.ik.gymrat.api.dto;

import com.ik.gymrat.persistence.entity.WorkoutSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutSetResponse {

    private long id;
    private int setNumber;
    private int reps;
    private float weight;

    public WorkoutSetResponse(WorkoutSet workoutSet) {
        this.id = workoutSet.getId();
        this.setNumber = workoutSet.getSetNumber();
        this.reps = workoutSet.getReps();
        this.weight = workoutSet.getWeight();
    }
}
