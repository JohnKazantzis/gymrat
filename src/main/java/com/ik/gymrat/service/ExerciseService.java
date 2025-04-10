package com.ik.gymrat.service;

import com.ik.gymrat.api.dto.ExerciseResponse;
import com.ik.gymrat.api.dto.MuscleGroupResponse;
import com.ik.gymrat.persistence.entity.Exercise;
import com.ik.gymrat.persistence.entity.MuscleGroup;
import com.ik.gymrat.persistence.repository.ExerciseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<ExerciseResponse> getExercisesByMuscleGroup(long muscleGroupId) {
        MuscleGroup muscleGroup = new MuscleGroup(muscleGroupId);
        List<Exercise> exercises = this.exerciseRepository.findByMuscleGroup(muscleGroup);
        return exercises.stream().map(
                exercise -> { return new ExerciseResponse(
                        exercise.getId(),
                        exercise.getName(),
                        new MuscleGroupResponse(exercise.getMuscleGroup().getId(), exercise.getMuscleGroup().getName())
                ); }
        ).collect(Collectors.toList());
    }

}
