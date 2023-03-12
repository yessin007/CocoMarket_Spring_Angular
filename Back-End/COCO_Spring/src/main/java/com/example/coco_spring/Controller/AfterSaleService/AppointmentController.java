package com.example.coco_spring.Controller.AfterSaleService;

import com.example.coco_spring.Entity.Appointment;
import com.example.coco_spring.Entity.Technician;
import com.example.coco_spring.Entity.User;
import com.example.coco_spring.Repository.AppointmentRepository;
import com.example.coco_spring.Repository.TechnicianRepository;
import com.example.coco_spring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private UserRepository userRepository;




        // endpoint pour récupérer tous les rendez-vous
        @GetMapping
        public List<Appointment> getAllAppointments() {
            return appointmentRepository.findAll();
        }

        // endpoint pour ajouter un rendez-vous
        @PostMapping
        public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
            Appointment savedAppointment = appointmentRepository.save(appointment);
            return ResponseEntity.created(URI.create("/appointments/" + savedAppointment.getId())).body(savedAppointment);
        }

        // endpoint pour mettre à jour un rendez-vous existant
        @PutMapping("/{id}")
        public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
            Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
            if (optionalAppointment.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            appointment.setId(id);
            Appointment updatedAppointment = appointmentRepository.save(appointment);
            return ResponseEntity.ok(updatedAppointment);
        }

        // endpoint pour supprimer un rendez-vous existant
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
            Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
            if (optionalAppointment.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            appointmentRepository.delete(optionalAppointment.get());
            return ResponseEntity.noContent().build();
        }
        @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAppointmentsForTimeRange(
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {
        List<Appointment> appointments = appointmentRepository.findByDateTimeBetween(startDateTime, endDateTime);
        return ResponseEntity.ok(appointments);
    }
    }

