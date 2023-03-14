package com.example.coco_spring.Controller.Delivery;

import com.example.coco_spring.Service.Delivery.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class LocationController {
    LocationService locationService;

    //@Scheduled(fixedRate = 5000) // Run every 30s
    @GetMapping("/map")
    public String getGeolocation() throws JsonProcessingException {
        System.out.println(locationService.getGeolocation());
        return locationService.getGeolocation();
    }

}