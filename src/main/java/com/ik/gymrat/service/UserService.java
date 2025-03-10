package com.ik.gymrat.service;

import com.ik.gymrat.api.dto.UserDTO;
import com.ik.gymrat.exceptions.UserNotFoundException;
import com.ik.gymrat.persistence.entity.User;
import com.ik.gymrat.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    private User mapToEntity(UserDTO userDTO, User currentUserData) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), currentUserData.getPassword());
    }

    public List<UserDTO> getAllUsers() {
        return this.userRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public UserDTO getUserById(long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return mapToDTO(user);
    }

    public UserDTO createUser(User user) {
        User newUser = this.userRepository.save(user);
        return mapToDTO(newUser);
    }

    public UserDTO updateUser(long id, UserDTO userDTO) {
        User currentUserData = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        userDTO.setId(id);
        User updatedUser = this.userRepository.save(mapToEntity(userDTO, currentUserData));
        return mapToDTO(updatedUser);
    }

    public void deleteUserById(long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        this.userRepository.delete(user);
    }
}
