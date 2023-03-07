package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByDeliveryDateBetweenAndAvailableTrue(LocalDate  startTime, LocalDate endTime);
}

