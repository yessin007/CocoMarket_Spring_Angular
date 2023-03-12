package com.example.coco_spring.Service.Delivery;

import com.example.coco_spring.Entity.ClientLocationRequest;
import com.example.coco_spring.Entity.User;
import com.example.coco_spring.Repository.UserRepository;
import com.example.coco_spring.Repository.clientLocationRequestRepository;
import com.example.coco_spring.Service.ICRUDService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
@AllArgsConstructor
public class LocationService implements ILocationService {
    //clientLocationRequestRepository clientLocationRequestRepository;

    public String getGeolocation() throws JsonProcessingException {
        WebClient webClient = WebClient.create();
        String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyAvHALSEQwNE3b-b7eHSZIDv-KK1wr7CRQ";
        Mono<String> result = webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        String response = result.block();

        /*
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        double latitude = root.get("location").get("lat").asDouble();
        double longitude = root.get("location").get("lng").asDouble();
        */

       /*
       ClientLocationRequest clientLocationRequest = new ClientLocationRequest();
        clientLocationRequest.setLatitude(latitude);
        clientLocationRequest.setLongitude(longitude);
        clientLocationRequestRepository.save(clientLocationRequest);
       */

        //return "Latitude: " + latitude + " Langitude : " + longitude;
        return response;
    }

    public String getLocation () throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ip-geo-location.p.rapidapi.com/ip/check?format=json"))
                .header("X-RapidAPI-Key", "9077ccf734msh7d2d2c5be06919dp1ff706jsn5da211b15318")
                .header("X-RapidAPI-Host", "ip-geo-location.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    return response.body();
    }
}
