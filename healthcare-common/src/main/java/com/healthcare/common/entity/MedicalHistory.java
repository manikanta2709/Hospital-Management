package com.healthcare.common.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Document(collection = "medical_history")
public class MedicalHistory {
    @Id
    private String id;
    
    @DBRef
    @Indexed
    private Patient patient;
    
    private LocalDate visitDate;
    
    private Doctor doctor;
    
    private String chiefComplaint;
    
    private String diagnosis;
    
    private List<String> medications;
    
    private String notes;
    
    private List<String> allergies;
    
    private List<String> chronicConditions;
    
    private Map<String, Object> vitalSigns;
    
    private List<String> labResults;
    
    private String followUpInstructions;
    
    // Constructors
    public MedicalHistory() {}
    
    public MedicalHistory(Patient patient, LocalDate visitDate, Doctor doctor, String chiefComplaint, String diagnosis) {
        this.patient = patient;
        this.visitDate = visitDate;
        this.doctor = doctor;
        this.chiefComplaint = chiefComplaint;
        this.diagnosis = diagnosis;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public LocalDate getVisitDate() {
        return visitDate;
    }
    
    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public String getChiefComplaint() {
        return chiefComplaint;
    }
    
    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }
    
    public String getDiagnosis() {
        return diagnosis;
    }
    
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    public List<String> getMedications() {
        return medications;
    }
    
    public void setMedications(List<String> medications) {
        this.medications = medications;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public List<String> getAllergies() {
        return allergies;
    }
    
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
    
    public List<String> getChronicConditions() {
        return chronicConditions;
    }
    
    public void setChronicConditions(List<String> chronicConditions) {
        this.chronicConditions = chronicConditions;
    }
    
    public Map<String, Object> getVitalSigns() {
        return vitalSigns;
    }
    
    public void setVitalSigns(Map<String, Object> vitalSigns) {
        this.vitalSigns = vitalSigns;
    }
    
    public List<String> getLabResults() {
        return labResults;
    }
    
    public void setLabResults(List<String> labResults) {
        this.labResults = labResults;
    }
    
    public String getFollowUpInstructions() {
        return followUpInstructions;
    }
    
    public void setFollowUpInstructions(String followUpInstructions) {
        this.followUpInstructions = followUpInstructions;
    }
}


