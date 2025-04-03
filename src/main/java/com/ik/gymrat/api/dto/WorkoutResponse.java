package com.ik.gymrat.api.dto;

import com.ik.gymrat.persistence.entity.WorkoutExercise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutResponse {
    private long id;
    private Date workoutDate;
}
