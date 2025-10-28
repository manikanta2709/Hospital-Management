package com.healthcare.api.controller;

import com.healthcare.api.service.AuthService;
import com.healthcare.common.entity.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/register/patient")
    public ResponseEntity<?> registerPatient(@Valid @RequestBody Patient patient) {
        try {
            Patient registeredPatient = authService.registerPatient(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredPatient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Registration failed: " + e.getMessage());
        }
    }
    
    @PostMapping("/register/doctor")
    public ResponseEntity<?> registerDoctor(@Valid @RequestBody Doctor doctor) {
        try {
            Doctor registeredDoctor = authService.registerDoctor(doctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredDoctor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Registration failed: " + e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody JwtRequest request) {
        try {
            JwtResponse response = authService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authentication failed: " + e.getMessage());
        }
    }
}


