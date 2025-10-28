package com.healthcare.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.healthcare.api.repository")
public class HealthcareAppointmentApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(HealthcareAppointmentApplication.class, args);
    }
}


