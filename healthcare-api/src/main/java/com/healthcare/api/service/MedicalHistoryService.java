package com.healthcare.api.service;

import com.healthcare.api.repository.MedicalHistoryRepository;
import com.healthcare.api.repository.PatientRepository;
import com.healthcare.api.repository.DoctorRepository;
import com.healthcare.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicalHistoryService {
    
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    public MedicalHistory createMedicalHistory(String patientId, LocalDate visitDate, String doctorId, 
                                               String chiefComplaint, String diagnosis, String notes) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        
        MedicalHistory history = new MedicalHistory(patient, visitDate, doctor, chiefComplaint, diagnosis);
        history.setNotes(notes);
        
        return medicalHistoryRepository.save(history);
    }
    
    public List<MedicalHistory> getPatientMedicalHistory(String patientId) {
        return medicalHistoryRepository.findByPatientIdOrderByVisitDateDesc(patientId);
    }
    
    public MedicalHistory getMedicalHistoryById(String id) {
        return medicalHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical history not found"));
    }
    
    public MedicalHistory updateMedicalHistory(String id, MedicalHistory historyDetails) {
        MedicalHistory history = medicalHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical history not found"));
        
        if (historyDetails.getDiagnosis() != null) {
            history.setDiagnosis(historyDetails.getDiagnosis());
        }
        if (historyDetails.getNotes() != null) {
            history.setNotes(historyDetails.getNotes());
        }
        if (historyDetails.getMedications() != null) {
            history.setMedications(historyDetails.getMedications());
        }
        if (historyDetails.getAllergies() != null) {
            history.setAllergies(historyDetails.getAllergies());
        }
        if (historyDetails.getChronicConditions() != null) {
            history.setChronicConditions(historyDetails.getChronicConditions());
        }
        if (historyDetails.getVitalSigns() != null) {
            history.setVitalSigns(historyDetails.getVitalSigns());
        }
        if (historyDetails.getLabResults() != null) {
            history.setLabResults(historyDetails.getLabResults());
        }
        if (historyDetails.getFollowUpInstructions() != null) {
            history.setFollowUpInstructions(historyDetails.getFollowUpInstructions());
        }
        
        return medicalHistoryRepository.save(history);
    }
}


