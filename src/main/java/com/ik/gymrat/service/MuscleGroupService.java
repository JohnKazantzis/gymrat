package com.ik.gymrat.service;

import com.ik.gymrat.exceptions.MuscleGroupNotFoundException;
import com.ik.gymrat.persistence.entity.MuscleGroup;
import com.ik.gymrat.persistence.repository.MuscleGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuscleGroupService {

    private final MuscleGroupRepository muscleGroupRepository;

    public MuscleGroupService(MuscleGroupRepository muscleGroupRepository) {
        this.muscleGroupRepository = muscleGroupRepository;
    }

    public List<MuscleGroup> getAllMuscleGroups() {
        return this.muscleGroupRepository.findAll();
    }

    public MuscleGroup getMuscleGroupById(long id) {
        return this.muscleGroupRepository.findById(id).orElseThrow(
            () -> new MuscleGroupNotFoundException("Muscle group with id: " + id + " not found")
        );
    }
}
