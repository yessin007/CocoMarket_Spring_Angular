package com.example.coco_spring.Service.TimeSlot;

import com.example.coco_spring.Entity.TimeSlot;
import com.example.coco_spring.Repository.TimeSlotRepository;
import com.example.coco_spring.Service.ICRUDService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TimeSlotService implements ICRUDService<TimeSlot,Long>, ITimeSlotService {
    TimeSlotRepository timeslotRepository;
    public List<TimeSlot> findByDeliveryDateBetweenAndAndAvailableTrue(LocalDate startDate, LocalDate endDate) {
        return timeslotRepository.findByDeliveryDateBetweenAndAvailableTrue(startDate,endDate);
    }
    public TimeSlot createTimeslot(TimeSlot timeslot) {
        return timeslotRepository.save(timeslot);
    }
    public TimeSlot updateTimeslot(Long id, TimeSlot timeslot) {
        TimeSlot existingTimeslot = timeslotRepository.findById(id).get();
        existingTimeslot.setStartTime(timeslot.getStartTime());
        existingTimeslot.setEndTime(timeslot.getEndTime());
        existingTimeslot.setDeliveryDate(timeslot.getDeliveryDate());
        existingTimeslot.setAvailable(timeslot.isAvailable());
        return timeslotRepository.save(existingTimeslot);
    }
   public TimeSlot updateTimeslotAvailability(Long id){
        TimeSlot existingTimeslot = timeslotRepository.findById(id).get();
        existingTimeslot.setAvailable(false);
        return timeslotRepository.save(existingTimeslot);
    }
    public void deleteTimeslot(Long id) {
        TimeSlot timeslot = timeslotRepository.findById(id).get();
        timeslotRepository.delete(timeslot);
    }
    @Override
    public List<TimeSlot> findAll() {
        return timeslotRepository.findAll();
    }
    @Override
    public TimeSlot retrieveItem(Long idItem) {
        return timeslotRepository.findById(idItem).get();
    }
    @Override
    public TimeSlot add(TimeSlot class1) {
        return timeslotRepository.save(class1);
    }
    @Override
    public void delete(Long aLong) {
        timeslotRepository.deleteById(aLong);
    }
    @Override
    public TimeSlot update(TimeSlot Classe1) {
        return timeslotRepository.save(Classe1);
    }
}
