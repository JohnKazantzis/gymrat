package com.ik.gymrat.service;

import com.ik.gymrat.api.dto.MuscleGroupResponse;
import com.ik.gymrat.exceptions.MuscleGroupNotFoundException;
import com.ik.gymrat.persistence.entity.MuscleGroup;
import com.ik.gymrat.persistence.repository.MuscleGroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MuscleGroupService {

    private final MuscleGroupRepository muscleGroupRepository;

    public MuscleGroupService(MuscleGroupRepository muscleGroupRepository) {
        this.muscleGroupRepository = muscleGroupRepository;
    }

    public List<MuscleGroupResponse> getAllMuscleGroups() {
        List<MuscleGroup> muscleGroups = this.muscleGroupRepository.findAll();
        List<MuscleGroupResponse> muscleGroupResponses = muscleGroups.stream().map(muscleGroup -> {
            return new MuscleGroupResponse(muscleGroup.getId(), muscleGroup.getName());
        }).collect(Collectors.toList());
        return muscleGroupResponses;
    }

    public MuscleGroup getMuscleGroupById(long id) {
        return this.muscleGroupRepository.findById(id).orElseThrow(
            () -> new MuscleGroupNotFoundException("Muscle group with id: " + id + " not found")
        );
    }
}
