package com.ik.gymrat.service;

import com.ik.gymrat.api.dto.WorkoutResponse;
import com.ik.gymrat.exceptions.UserNotFoundException;
import com.ik.gymrat.persistence.entity.User;
import com.ik.gymrat.persistence.entity.Workout;
import com.ik.gymrat.persistence.repository.UserRepository;
import com.ik.gymrat.persistence.repository.WorkoutRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    public Page<WorkoutResponse> getAllWorkouts(int page, int size, long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
        Page<Workout> workouts = this.workoutRepository.findAllByUser(user, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "workoutDate")));
        return workouts.map(workout -> new WorkoutResponse(workout));
    }

}
