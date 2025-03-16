package com.ik.gymrat.service;

import com.ik.gymrat.api.dto.MessageResponse;
import com.ik.gymrat.api.dto.SignupRequest;
import com.ik.gymrat.api.dto.UserDTO;
import com.ik.gymrat.exceptions.UserExistsException;
import com.ik.gymrat.exceptions.UserNotFoundException;
import com.ik.gymrat.persistence.entity.ERole;
import com.ik.gymrat.persistence.entity.Role;
import com.ik.gymrat.persistence.entity.User;
import com.ik.gymrat.persistence.repository.RoleRepository;
import com.ik.gymrat.persistence.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail());
    }

    private User mapToEntity(UserDTO userDTO, User currentUserData) {
        return new User(userDTO.getUsername(), userDTO.getEmail(), currentUserData.getPassword());
    }

    public List<UserDTO> getAllUsers() {
        return this.userRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public UserDTO getUserById(long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return mapToDTO(user);
    }

    public void createUser(SignupRequest request) {
        // Check that the user is not duplicate based on username or email
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new UserExistsException("Error: Username is already in use!");
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new UserExistsException("Error: Email is already in use!");
        }

        // Create new user
        User user = new User(request.getUsername(), request.getEmail(), encoder.encode(request.getPassword()));
        Set<String> strRoles = request.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRoles == null || strRoles.isEmpty()) {
            Role userRole = roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            List<Role> rolesRetrieved = roleRepository.findByNameIn(
                strRoles.stream().map(role -> ERole.valueOf(role)).collect(Collectors.toSet())
            );
            roles.addAll(rolesRetrieved);
        }

        user.setRoles(roles);
        userRepository.save(user);
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
