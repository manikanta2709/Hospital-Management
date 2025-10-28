package com.healthcare.api.service;

import com.healthcare.api.repository.AppointmentRepository;
import com.healthcare.api.repository.DoctorRepository;
import com.healthcare.api.repository.PatientRepository;
import com.healthcare.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    public Appointment bookAppointment(String patientId, String doctorId, LocalDateTime appointmentDateTime, String reason) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        
        // Check if appointment time is in the past
        if (appointmentDateTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cannot book appointment in the past");
        }
        
        // Check if doctor is already booked at this time
        List<Appointment> existingAppointments = appointmentRepository
                .findByDoctorIdAndStatus(doctorId, Appointment.AppointmentStatus.SCHEDULED);
        
        for (Appointment existing : existingAppointments) {
            if (existing.getAppointmentDateTime().equals(appointmentDateTime)) {
                throw new RuntimeException("Doctor already has an appointment at this time");
            }
        }
        
        Appointment appointment = new Appointment(patient, doctor, appointmentDateTime, reason);
        appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED);
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setUpdatedAt(LocalDateTime.now());
        
        return appointmentRepository.save(appointment);
    }
    
    public List<Appointment> getPatientAppointments(String patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
    
    public List<Appointment> getDoctorAppointments(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
    
    public Appointment getAppointmentById(String id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }
    
    public Appointment rescheduleAppointment(String id, LocalDateTime newDateTime, String reason) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        
        if (appointment.getStatus() == Appointment.AppointmentStatus.CANCELLED) {
            throw new RuntimeException("Cannot reschedule a cancelled appointment");
        }
        
        if (appointment.getStatus() == Appointment.AppointmentStatus.COMPLETED) {
            throw new RuntimeException("Cannot reschedule a completed appointment");
        }
        
        if (newDateTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cannot reschedule appointment in the past");
        }
        
        // Check if doctor is available at new time
        String doctorId = appointment.getDoctor().getId();
        List<Appointment> existingAppointments = appointmentRepository
                .findByDoctorId(doctorId);
        
        for (Appointment existing : existingAppointments) {
            if (!existing.getId().equals(id) && 
                existing.getStatus() != Appointment.AppointmentStatus.CANCELLED &&
                existing.getAppointmentDateTime().equals(newDateTime)) {
                throw new RuntimeException("Doctor already has an appointment at this time");
            }
        }
        
        appointment.setAppointmentDateTime(newDateTime);
        appointment.setReason(reason);
        appointment.setStatus(Appointment.AppointmentStatus.RESCHEDULED);
        appointment.setUpdatedAt(LocalDateTime.now());
        
        return appointmentRepository.save(appointment);
    }
    
    public void cancelAppointment(String id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        
        if (appointment.getStatus() == Appointment.AppointmentStatus.COMPLETED) {
            throw new RuntimeException("Cannot cancel a completed appointment");
        }
        
        appointment.setStatus(Appointment.AppointmentStatus.CANCELLED);
        appointment.setUpdatedAt(LocalDateTime.now());
        appointmentRepository.save(appointment);
    }
}


