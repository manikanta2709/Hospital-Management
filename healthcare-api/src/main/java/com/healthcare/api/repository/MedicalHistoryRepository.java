package com.healthcare.api.repository;

import com.healthcare.common.entity.MedicalHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory, String> {
    List<MedicalHistory> findByPatientId(String patientId);
    List<MedicalHistory> findByPatientIdOrderByVisitDateDesc(String patientId);
}


