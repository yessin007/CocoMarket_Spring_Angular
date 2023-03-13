package com.example.coco_spring.Service.TimeSlot;

import com.example.coco_spring.Entity.TimeSlot;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

public interface ITimeSlotService {
     List<TimeSlot> findByDeliveryDateBetweenAndAndAvailableTrue(LocalDate startDate, LocalDate endDate);
     TimeSlot createTimeslot(TimeSlot timeslot);
    TimeSlot updateTimeslot(Long id, TimeSlot timeslot);
    void deleteTimeslot(Long id);
}
