package com.healthcare.api.repository;

import com.healthcare.common.entity.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
    Optional<Doctor> findByUsername(String username);
    Optional<Doctor> findByEmail(String email);
    List<Doctor> findBySpecialization(String specialization);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}


