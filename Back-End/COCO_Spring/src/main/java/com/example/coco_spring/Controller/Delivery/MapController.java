package com.example.coco_spring.Controller.Delivery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.io.IOException;

@RequestMapping("/api")
@RestController
public class MapController {
    /*@PostMapping("/map")
    public String geocode() throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAEWKCXdWuNYu5Knz-FCYJvgKJO0UFRHh8\n")
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context, "france").await();
        LatLng location = results[0].geometry.location;
        return "Latitude: " + location.lat + ", Longitude: " + location.lng;
*/
    @GetMapping("/map")
    public void getGeolocation() throws InterruptedException, IOException {
        WebClient webClient = WebClient.create();

        String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyAvHALSEQwNE3b-b7eHSZIDv-KK1wr7CRQ";

        Mono<String> result = webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        result.subscribe(response -> {
            // Traitement des résultats de la réponse
            System.out.println(response);
        });

    }
}