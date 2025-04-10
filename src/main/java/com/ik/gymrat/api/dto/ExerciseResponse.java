package com.ik.gymrat.api.dto;

import com.ik.gymrat.persistence.entity.MuscleGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ExerciseResponse {

    private long id;
    private String name;
    private MuscleGroupResponse muscleGroup;

}
