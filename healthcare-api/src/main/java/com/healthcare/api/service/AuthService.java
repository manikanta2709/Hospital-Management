package com.healthcare.api.service;

import com.healthcare.api.repository.PatientRepository;
import com.healthcare.api.repository.DoctorRepository;
import com.healthcare.api.util.JwtUtil;
import com.healthcare.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    public Patient registerPatient(Patient patient) {
        if (patientRepository.existsByUsername(patient.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        return patientRepository.save(patient);
    }
    
    public Doctor registerDoctor(Doctor doctor) {
        if (doctorRepository.existsByUsername(doctor.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        return doctorRepository.save(doctor);
    }
    
    public JwtResponse authenticate(JwtRequest request) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        String username = authentication.getName();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Find user to get roles
        Patient patient = patientRepository.findByUsername(username).orElse(null);
        Doctor doctor = null;
        
        if (patient == null) {
            doctor = doctorRepository.findByUsername(username).orElse(null);
        }
        
        String email;
        if (patient != null) {
            email = patient.getEmail();
        } else if (doctor != null) {
            email = doctor.getEmail();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        
        // Generate token
        List<String> roles = new ArrayList<>();
        userDetails.getAuthorities().forEach(authority -> roles.add(authority.getAuthority()));
        
        String token = jwtUtil.generateToken(username, roles);
        
        return new JwtResponse(token, username, email, roles);
    }
}


