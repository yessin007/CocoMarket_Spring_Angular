package com.example.coco_spring.Controller.AfterSaleService;

import com.example.coco_spring.Entity.Technician;
import com.example.coco_spring.Entity.Ticket;
import com.example.coco_spring.Repository.TechnicianRepository;
import com.example.coco_spring.Repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@AllArgsConstructor
@RequestMapping("/api/ticket")

public class TicketController {
    TicketRepository ticketRepository ;
    TechnicianRepository technicianRepository ;


    @PostMapping("/assign/{ticketId}/{technicianId}")
    public ResponseEntity<Ticket> assignTicket(@PathVariable Long ticketId, @PathVariable Long technicianId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        Optional<Technician> optionalTechnician = technicianRepository.findById(technicianId);

        if (!optionalTicket.isPresent() || !optionalTechnician.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Ticket ticket = optionalTicket.get();
        Technician technician = optionalTechnician.get();
        ticket.setTechnician(technician);

        ticketRepository.save(ticket);

        return ResponseEntity.ok(ticket);
    }
    @GetMapping("/{technicianId}/tickets")
    public ResponseEntity<List<Ticket>> getAssignedTickets(@PathVariable Long technicianId) {
        Optional<Technician> optionalTechnician = technicianRepository.findById(technicianId);

        if (!optionalTechnician.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Technician tt = optionalTechnician.get();
        List<Ticket> assignedTickets = ticketRepository.findByTechnicianName(tt.getName());

        return ResponseEntity.ok(assignedTickets);
    }
}
