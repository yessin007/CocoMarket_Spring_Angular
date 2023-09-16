package com.example.coco_spring.Service.Provider;

import com.example.coco_spring.Entity.ProviderLocation;
import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Entity.ProviderRating;
import com.example.coco_spring.Entity.User;
import com.example.coco_spring.Repository.ProviderLocationRepository;
import com.example.coco_spring.Repository.ProviderRatingRepository;
import com.example.coco_spring.Repository.ProviderRepository;
import com.example.coco_spring.Repository.UserRepository;
import com.example.coco_spring.Service.Delivery.LocationService;
import com.example.coco_spring.Service.ICRUDService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class ProviderService implements ICRUDService<Provider,Long>, IProviderService {
    private final ProviderLocationRepository providerLocationRepository;

    ProviderRepository providerRepository;
    ProviderRatingRepository providerRatingRepository;
    UserRepository userRepository;
    LocationService locationService;
    @Override
    public List<Provider> findAll() {

        return providerRepository.findAll();
    }

    @Override
    public Provider retrieveItem(Long providerId) {

        return providerRepository.findById(providerId).get();
    }

    @Override
    public Provider add(Provider provider) {

        return providerRepository.save(provider);
    }

    @Override
    public void delete(Long providerId) {

        providerRepository.deleteById(providerId);
    }

    @Override
    public Provider update(Provider delivery) {

        return providerRepository.save(delivery);
    }
    /*public Provider rateProvider (Long providerId, int nb_etouile) {
        Provider provider = providerRepository.findById(providerId).get();
        provider.setNb_etoil(nb_etouile);
        return providerRepository.save(provider);
    }
*/
    public ResponseEntity<Map<String, Object>> setLatLngToProvider(Long providerId) {
        try {
            String geolocationResponse = locationService.getGeolocation();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(geolocationResponse);
            double latitude = root.get("location").get("lat").asDouble();
            double Langitude = root.get("location").get("lng").asDouble();
            ProviderLocation providerLocation = new ProviderLocation();
            providerLocation.setLatitude(latitude);
            providerLocation.setLongitude(Langitude);
            providerLocationRepository.save(providerLocation);
            Provider provider = providerRepository.findById(providerId).get();
            AssignLocationtoProvider(providerLocation.getId(),provider.getProviderId());

            Map<String, Object> response = new HashMap<>();
            response.put("latitude", latitude);
            response.put("langitude", Langitude);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public void AssignLocationtoProvider(Long locationId, Long providerId) {
        Provider provider = providerRepository.findById(providerId).get();
        ProviderLocation providerLocation = providerLocationRepository.findById(locationId).get();
        provider.setProviderLocation(providerLocation);
        providerRepository.save(provider);
    }
    public String getAddressToCoordinates(String origin,String destination) throws IOException {

        String apiKey = "AIzaSyDhfIOs9AylSRMc-WGwfuDWwQa_jy2RUj0";

        WebClient webClient = WebClient.create();
        String urrl = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations=" + destination + "&key=" + apiKey;
        Mono<String> result = webClient.post()
                .uri(urrl)
                .retrieve()
                .bodyToMono(String.class);
        String response = result.block();

        return response;
    }


    public ProviderRating rateProvider(Long providerId, Long userId, Integer rating) {
        Provider provider = providerRepository.findById(providerId).get();
        User user = userRepository.findById(userId).get();
        ProviderRating providerRating = new ProviderRating();


        providerRating.setProvider(provider);
        providerRating.setUser(user);
        providerRating.setRating(rating);

        return providerRatingRepository.save(providerRating);
    }
    public List<ProviderRating> getProviderRatings(Long providerId) {
        return providerRatingRepository.findByProviderProviderId(providerId);
    }

    public Double getAverageRatingByProviderId(Long providerId) {
        return providerRatingRepository.findAverageRatingByProviderId(providerId);
    }

    public List<Object[]> getMostRatedProviders() {
        return providerRatingRepository.findMostRatedProviders();
    }

}
