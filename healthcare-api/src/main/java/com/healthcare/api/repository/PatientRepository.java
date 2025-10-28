package com.healthcare.api.repository;

import com.healthcare.common.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    Optional<Patient> findByUsername(String username);
    Optional<Patient> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}


