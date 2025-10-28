package com.healthcare.api.controller;

import com.healthcare.api.service.MedicalHistoryService;
import com.healthcare.common.entity.MedicalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/medical-history")
@CrossOrigin(origins = "http://localhost:3000")
public class MedicalHistoryController {
    
    @Autowired
    private MedicalHistoryService medicalHistoryService;
    
    @PostMapping("/create")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> createMedicalHistory(
            @RequestParam String patientId,
            @RequestParam String visitDate,
            @RequestParam String doctorId,
            @RequestParam String chiefComplaint,
            @RequestParam String diagnosis,
            @RequestParam(required = false) String notes) {
        try {
            LocalDate date = LocalDate.parse(visitDate);
            MedicalHistory history = medicalHistoryService.createMedicalHistory(
                    patientId, date, doctorId, chiefComplaint, diagnosis, notes);
            return ResponseEntity.status(HttpStatus.CREATED).body(history);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to create medical history: " + e.getMessage());
        }
    }
    
    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('PATIENT', 'DOCTOR')")
    public ResponseEntity<List<MedicalHistory>> getPatientMedicalHistory(@PathVariable String patientId) {
        List<MedicalHistory> history = medicalHistoryService.getPatientMedicalHistory(patientId);
        return ResponseEntity.ok(history);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PATIENT', 'DOCTOR')")
    public ResponseEntity<MedicalHistory> getMedicalHistory(@PathVariable String id) {
        MedicalHistory history = medicalHistoryService.getMedicalHistoryById(id);
        return ResponseEntity.ok(history);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> updateMedicalHistory(@PathVariable String id, @RequestBody MedicalHistory historyDetails) {
        try {
            MedicalHistory history = medicalHistoryService.updateMedicalHistory(id, historyDetails);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to update medical history: " + e.getMessage());
        }
    }
}


