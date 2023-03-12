package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment ,Long> {
    List<Appointment> findByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
