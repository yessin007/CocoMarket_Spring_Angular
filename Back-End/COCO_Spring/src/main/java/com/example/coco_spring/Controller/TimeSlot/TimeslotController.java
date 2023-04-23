package com.example.coco_spring.Controller.TimeSlot;

import com.example.coco_spring.Entity.TimeSlot;
import com.example.coco_spring.Service.TimeSlot.TimeSlotService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/timeslots")
public class TimeslotController {
    TimeSlotService timeslotService;

    @GetMapping("/available")
    public List<TimeSlot> getAvailableTimeslots(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return timeslotService.findByDeliveryDateBetweenAndAndAvailableTrue(startDate, endDate);
    }

    @PostMapping
    public TimeSlot createTimeslot(@RequestBody TimeSlot timeslot) {
        return timeslotService.createTimeslot(timeslot);
    }

    @PutMapping("/update/{id}")
    public TimeSlot updateTimeslot(@PathVariable("id") Long id, @RequestBody TimeSlot timeslot) {
        return timeslotService.updateTimeslot(id, timeslot);
    }

    @PutMapping("/updateAvailability/{id}")
    public TimeSlot updateAvailability(@PathVariable Long id) {
        return timeslotService.updateTimeslotAvailability(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTimeslot(@PathVariable("id") Long id) {
        timeslotService.deleteTimeslot(id);
    }
    @PostMapping("/assignDateToDelivery/{timeId}/{deliveryId}")
    public void assignDateToDelivery(@PathVariable("timeId") Long timeId,@PathVariable("deliveryId") Long deliveryId){
        timeslotService.assignDateToDelivery(timeId,deliveryId);
    }

    }
