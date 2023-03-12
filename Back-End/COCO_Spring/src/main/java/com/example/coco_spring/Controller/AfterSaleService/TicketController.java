package com.example.coco_spring.Controller.AfterSaleService;

import com.example.coco_spring.Entity.Technician;
import com.example.coco_spring.Entity.Ticket;
import com.example.coco_spring.Repository.TechnicianRepository;
import com.example.coco_spring.Repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

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
    @GetMapping("/tickets/average-response-time")
    public ResponseEntity<Double> getAverageResponseTime() {
        // Récupération des données des tickets à partir de la base de données
        List<Ticket> tickets = ticketRepository.findAll();
        double[] responseTimes = tickets.stream().mapToDouble(Ticket::getResponseTime).toArray();

        // Calcul du temps de réponse moyen des tickets
        DescriptiveStatistics stats = new DescriptiveStatistics(responseTimes);
        double averageResponseTime = stats.getMean();

        // Envoi de la réponse avec le temps de réponse moyen
        return ResponseEntity.ok(averageResponseTime);
    }
    // ...

    @GetMapping("/tickets/average-resolution-time")
    public ResponseEntity<Double> getAverageResolutionTime() {
        // Récupération des données des tickets à partir de la base de données
        List<Ticket> tickets = ticketRepository.findAll();

        // Calcul de la durée moyenne de résolution des problèmes
        long totalResolutionTime = 0;
        int numResolvedTickets = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getStatus().equals("Résolu")) {
                LocalDateTime openDateTime = ticket.getOpenDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime closeDateTime = ticket.getCloseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                Duration duration = Duration.between(openDateTime, closeDateTime);
                totalResolutionTime += duration.getSeconds();
                numResolvedTickets++;
            }
        }
        double averageResolutionTime = (double) totalResolutionTime / numResolvedTickets;

        // Envoi de la réponse avec la durée moyenne de résolution des problèmes
        return ResponseEntity.ok(averageResolutionTime);
    }
    @GetMapping("/tickets/most-common-problems")
    public ResponseEntity<List<String>> getMostCommonProblems() {
        // Récupération des données des tickets à partir de la base de données
        List<Ticket> tickets = ticketRepository.findAll();

        // Comptage des occurrences de chaque problème
        Map<String, Integer> problemCounts = new HashMap<>();
        for (Ticket ticket : tickets) {
            String problem = ticket.getSubject();
            int count = problemCounts.getOrDefault(problem, 0);
            problemCounts.put(problem, count + 1);
        }

        // Tri des problèmes par ordre décroissant de leur nombre d'occurrences
        List<Map.Entry<String, Integer>> sortedProblemCounts = new ArrayList<>(problemCounts.entrySet());
        sortedProblemCounts.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Sélection des problèmes les plus courants
        List<String> mostCommonProblems = new ArrayList<>();
        for (int i = 0; i < 5 && i < sortedProblemCounts.size(); i++) {
            String problem = sortedProblemCounts.get(i).getKey();
            mostCommonProblems.add(problem);
        }

        // Envoi de la réponse avec les problèmes les plus courants
        return ResponseEntity.ok(mostCommonProblems);
    }

}

