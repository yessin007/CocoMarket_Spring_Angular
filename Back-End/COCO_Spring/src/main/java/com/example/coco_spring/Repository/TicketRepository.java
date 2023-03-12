package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket ,Long> {
    List<Ticket> findByTechnicianName(String technician);
}
