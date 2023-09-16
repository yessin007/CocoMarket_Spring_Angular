package com.example.coco_spring.Service.Delivery;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class LocationService {
    //clientLocationRequestRepository clientLocationRequestRepository;
    ProductRepository productRepository;
    public String getGeolocation() throws JsonProcessingException {
        WebClient webClient = WebClient.create();
        String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyAvHALSEQwNE3b-b7eHSZIDv-KK1wr7CRQ";
        Mono<String> result = webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        String response = result.block();


        /*ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        double latitude = root.get("location").get("lat").asDouble();
        double longitude = root.get("location").get("lng").asDouble();
        System.out.println(response);
        */


       /*
       ClientLocationRequest clientLocationRequest = new ClientLocationRequest();
        clientLocationRequest.setLatitude(latitude);
        clientLocationRequest.setLongitude(longitude);
        clientLocationRequestRepository.save(clientLocationRequest);
       */

       // return "Latitude: " + latitude + " Langitude : " + longitude;
        return response;
    }

   /* public Map<String , StoreLocations> getNearestStorewithproduct(String productname, double clientLatitude,
                                                                   double clientLongitude){

        List<Product> products = productRepository.findByTitle(productname);
        StoreLocations storeLocations = null;
        double shortestDistance = Double.MAX_VALUE;
        String storeName = null;
        Map<String,StoreLocations> map= new HashMap<>();
        for (Product product1 : products ) {
            Store store = product1.getStoress();

            double distance = distanceInKm(clientLatitude, clientLongitude, store.getStoreLocations().getLatitude(), store.getStoreLocations().getLongitude());
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    storeName = product1.getStoress().getStoreName();
                   // storeLocations =product1.getStoress().getStoreLocations();
                }
            }
        map.put(storeName, storeLocations);
        return map;
    } */
    private double distanceInKm(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344; // convert to kilometers
        return dist;
    }}
