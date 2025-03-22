package com.ik.gymrat.service;

import com.ik.gymrat.exceptions.WorkoutSetNotFoundException;
import com.ik.gymrat.persistence.entity.WorkoutExercise;
import com.ik.gymrat.persistence.entity.WorkoutSet;
import com.ik.gymrat.persistence.repository.WorkoutSetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutSetService {

    private final WorkoutSetRepository workoutSetRepository;

    public WorkoutSetService(WorkoutSetRepository workoutSetRepository) {
        this.workoutSetRepository = workoutSetRepository;
    }

    public WorkoutSet getWorkoutSetById(long id) {
        System.out.println("getWorkoutSetById 2: " + id);
        WorkoutSet workoutSet = this.workoutSetRepository.findById(id).orElseThrow(() -> {
            throw new WorkoutSetNotFoundException("The workout set with id: " + " does not exist");
        });
        System.out.println("getWorkoutSetById 3: " + workoutSet);
        return workoutSet;
    }

    public List<WorkoutSet> getWorkoutSetsByWorkoutExercise(long exerciseId) {
        WorkoutExercise workoutExercise = new WorkoutExercise(exerciseId);
        return this.workoutSetRepository.findByWorkoutExercise(workoutExercise);
    }

    public WorkoutSet upsertWorkoutSet(WorkoutSet workoutSet) {
        return this.workoutSetRepository.save(workoutSet);
    }

    public void deleteWorkoutSet(long id) {
        WorkoutSet workoutSet = this.getWorkoutSetById(id);
        this.workoutSetRepository.delete(workoutSet);
    }
}
