package com.ik.gymrat.exceptions;

public class WorkoutSetNotFoundException extends RuntimeException {
    public WorkoutSetNotFoundException(String message) {
        super(message);
    }
}
