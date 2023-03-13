package com.example.coco_spring.Service.Provider;

import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Entity.ProviderRating;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IProviderService {
     //Provider rateProvider (Long providerId, int nb_etouile);
     ResponseEntity<Map<String, Object>> setLatLngToProvider(Long providerId);
     ProviderRating rateProvider(Long providerId, Long userId, Integer rating);
     List<ProviderRating> getProviderRatings(Long providerId);
     Double getAverageRatingByProviderId(Long providerId);
     List<Object[]> getMostRatedProviders();

}
