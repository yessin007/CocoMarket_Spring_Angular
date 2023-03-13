package com.example.coco_spring.Service.Provider;

import com.example.coco_spring.Entity.Provider;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IProviderService {
     Provider rateProvider (Long providerId, int nb_etouile);
     ResponseEntity<Map<String, Object>> setLatLngToProvider(Long providerId);
}
