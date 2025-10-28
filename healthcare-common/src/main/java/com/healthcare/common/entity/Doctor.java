package com.healthcare.common.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "doctors")
public class Doctor {
    @Id
    private String id;
    
    @NotBlank(message = "Username is required")
    @Indexed(unique = true)
    private String username;
    
    @NotBlank(message = "Password is required")
    private String password;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Indexed(unique = true)
    private String email;
    
    @NotBlank(message = "Full name is required")
    private String fullName;
    
    @NotBlank(message = "Specialization is required")
    private String specialization;
    
    @NotBlank(message = "License number is required")
    private String licenseNumber;
    
    private String phoneNumber;
    private String bio;
    private List<String> availability = new ArrayList<>();
    
    private List<String> role = new ArrayList<>(List.of("DOCTOR"));
    
    // Constructors
    public Doctor() {}
    
    public Doctor(String username, String password, String email, String fullName, String specialization, String licenseNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public List<String> getAvailability() {
        return availability;
    }
    
    public void setAvailability(List<String> availability) {
        this.availability = availability;
    }
    
    public List<String> getRole() {
        return role;
    }
    
    public void setRole(List<String> role) {
        this.role = role;
    }
}


