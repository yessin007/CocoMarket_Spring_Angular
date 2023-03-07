package com.example.coco_spring.Entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "timeslot")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate deliveryDate;
    @Column(name = "available")
    private boolean available;

    public boolean Available() {
        return available;
    }
    public void setAvailable(boolean isAvailable) {
        this.available = isAvailable;
    }
}


