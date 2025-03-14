package com.ik.gymrat.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.ik.gymrat.exceptions.UserExistsException;
import com.ik.gymrat.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ik.gymrat.api.dto.LoginRequest;
import com.ik.gymrat.api.dto.SignupRequest;
import com.ik.gymrat.api.dto.JwtResponse;
import com.ik.gymrat.api.dto.MessageResponse;
import com.ik.gymrat.persistence.repository.RoleRepository;
import com.ik.gymrat.persistence.repository.UserRepository;
import com.ik.gymrat.security.jwt.JwtUtils;
import com.ik.gymrat.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    private final UserService userService;

    public AuthController(
        UserService userService,
        AuthenticationManager authenticationManager,
        PasswordEncoder encoder,
        JwtUtils jwtUtils
    ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("SIGNIN");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        this.userService.createUser(signUpRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<String> handUserExistsException(UserExistsException e) {
        System.out.println("User already exists");
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        System.out.println("An error occured: " + e.getMessage());
        return ResponseEntity.internalServerError().build();
    }

}