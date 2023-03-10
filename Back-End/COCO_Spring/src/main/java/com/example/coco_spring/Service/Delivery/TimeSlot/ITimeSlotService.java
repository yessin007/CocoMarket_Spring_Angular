package com.example.coco_spring.Service.Delivery.TimeSlot;

import com.example.coco_spring.Entity.TimeSlot;

import java.time.LocalDate;
import java.util.List;

public interface ITimeSlotService {
     List<TimeSlot> findByDeliveryDateBetweenAndAndAvailableTrue(LocalDate startDate, LocalDate endDate);
     TimeSlot createTimeslot(TimeSlot timeslot);
    TimeSlot updateTimeslot(Long id, TimeSlot timeslot);
    void deleteTimeslot(Long id);
}
