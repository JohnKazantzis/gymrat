package com.ik.gymrat.persistence.entity;

import jakarta.persistence.*;
import com.ik.gymrat.persistence.entity.MuscleGroup;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "muscle_group_id")
    private MuscleGroup muscleGroup;
}
