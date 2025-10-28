package com.healthcare.api.service;

import com.healthcare.api.repository.PatientRepository;
import com.healthcare.api.repository.DoctorRepository;
import com.healthcare.common.entity.Patient;
import com.healthcare.common.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find patient first
        Patient patient = patientRepository.findByUsername(username).orElse(null);
        
        if (patient != null) {
            return User.builder()
                    .username(patient.getUsername())
                    .password(patient.getPassword())
                    .authorities(patient.getRole().stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .collect(Collectors.toList()))
                    .build();
        }
        
        // Try to find doctor
        Doctor doctor = doctorRepository.findByUsername(username).orElse(null);
        
        if (doctor != null) {
            return User.builder()
                    .username(doctor.getUsername())
                    .password(doctor.getPassword())
                    .authorities(doctor.getRole().stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .collect(Collectors.toList()))
                    .build();
        }
        
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}


