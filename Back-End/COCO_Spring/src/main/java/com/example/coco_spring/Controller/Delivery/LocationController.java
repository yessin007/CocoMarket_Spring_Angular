package com.example.coco_spring.Controller.Delivery;

import com.example.coco_spring.Entity.ClientLocationRequest;
import com.example.coco_spring.Entity.deliveries;
import com.example.coco_spring.Repository.DeliveryRepository;
import com.example.coco_spring.Repository.clientLocationRequestRepository;
import com.example.coco_spring.Service.Delivery.DeliveryService;
import com.example.coco_spring.Service.Delivery.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class LocationController {
    LocationService locationService;

    //@Scheduled(fixedRate = 5000) // Run every 30s
    @GetMapping("/map")
    public String getGeolocation() throws JsonProcessingException {
        return locationService.getGeolocation();
    }
}