package com.healthcare.api.controller;

import com.healthcare.api.service.AppointmentService;
import com.healthcare.common.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {
    
    @Autowired
    private AppointmentService appointmentService;
    
    @PostMapping("/book")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> bookAppointment(
            @RequestParam String patientId,
            @RequestParam String doctorId,
            @RequestParam String appointmentDateTime,
            @RequestParam String reason,
            Authentication authentication) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(appointmentDateTime);
            Appointment appointment = appointmentService.bookAppointment(patientId, doctorId, dateTime, reason);
            return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Booking failed: " + e.getMessage());
        }
    }
    
    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR')")
    public ResponseEntity<List<Appointment>> getPatientAppointments(@PathVariable String patientId) {
        List<Appointment> appointments = appointmentService.getPatientAppointments(patientId);
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/doctor/{doctorId}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<List<Appointment>> getDoctorAppointments(@PathVariable String doctorId) {
        List<Appointment> appointments = appointmentService.getDoctorAppointments(doctorId);
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PATIENT', 'DOCTOR')")
    public ResponseEntity<Appointment> getAppointment(@PathVariable String id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }
    
    @PutMapping("/{id}/reschedule")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> rescheduleAppointment(
            @PathVariable String id,
            @RequestParam String newDateTime,
            @RequestParam String reason) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(newDateTime);
            Appointment appointment = appointmentService.rescheduleAppointment(id, dateTime, reason);
            return ResponseEntity.ok(appointment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Rescheduling failed: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}/cancel")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR')")
    public ResponseEntity<?> cancelAppointment(@PathVariable String id) {
        try {
            appointmentService.cancelAppointment(id);
            return ResponseEntity.ok("Appointment cancelled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cancellation failed: " + e.getMessage());
        }
    }
}


