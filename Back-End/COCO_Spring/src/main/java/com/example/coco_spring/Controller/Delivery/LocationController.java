package com.example.coco_spring.Controller.Delivery;

import com.example.coco_spring.Entity.ClientLocationRequest;
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

import java.io.IOException;
import java.net.http.HttpResponse;

@RequestMapping("/api")
@RestController
public class LocationController {
    //@Scheduled(fixedRate = 5000) // Run every 30s
    @GetMapping("/map")
    public ResponseEntity<String> getGeolocation() {
        WebClient webClient = WebClient.create();
        String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyAvHALSEQwNE3b-b7eHSZIDv-KK1wr7CRQ";
        Mono<String> result = webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        String response = result.block(); // This will block the thread until the response is received
        return ResponseEntity.ok(response);
    }
    @GetMapping("/map1")
    public String getLocation () throws IOException, InterruptedException {
        return locationService.getLocation();
    }
}