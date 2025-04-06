package com.ik.gymrat.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workout_sets")
public class WorkoutSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "set_number")
    private int setNumber;

    @Column(name = "reps")
    private int reps;

    @Column(name = "weight")
    private float weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_exercise_id")
    private WorkoutExercise workoutExercise;
}
