package com.example.coco_spring.Controller.Provider;

import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Entity.ProviderRating;
import com.example.coco_spring.Service.Provider.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/provider/")
public class ProviderController {

    ProviderService providerService;
    @GetMapping("/retrive_all_providers")
    public List<Provider> retrieveDeliveryList(){

        return providerService.findAll();
    }

    @GetMapping("/retrive_provider/{providerId}")
    public Provider retrieveProvider(@PathVariable("providerId") Long providerId){

        return providerService.retrieveItem(providerId);
    }

    @PostMapping("/add_provider")
    public Provider addProvider(@RequestBody Provider provider){

        return providerService.add(provider);
    }

    @PutMapping("/update_provider")
    public Provider updateProvider(@RequestBody Provider provider){

        return providerService.update(provider);
    }

    @DeleteMapping("/delete_provider/{providerId}")
    public void deleteProvider(@PathVariable("providerId") Long providerId){

        providerService.delete(providerId);
    }
    /*@PostMapping("/rateProvider/{providerId}/{nb_etouile}")
    public Provider rateProvider(@PathVariable("providerId") Long providerId,@PathVariable("nb_etouile") int nb_etouile){
        return providerService.rateProvider(providerId,nb_etouile);
    }*/

    @GetMapping("/setLatLng/{providerId}")
    public ResponseEntity<Map<String, Object>> setLatLngToProvider(@PathVariable("providerId") Long providerId){
        return providerService.setLatLngToProvider(providerId);
    }
    /*@GetMapping("/listOfMostProviders")
    public List<Object[]> listOfMostProviders(){
        return providerService.listOfMostProviders();
    }*/
    @PostMapping("/rateProvider/{providerId}/{userId}")
    public ProviderRating rateProvider(@PathVariable("providerId") Long providerId,@PathVariable("userId") Long userId,@RequestParam Integer rating){
        return providerService.rateProvider(providerId,userId,rating);
    }
    @GetMapping("/getProviderRatings/{providerId}")
    public List<ProviderRating> getProviderRatings(@PathVariable("providerId") Long providerId){
        return providerService.getProviderRatings(providerId);
    }

    @GetMapping("/getAverageRatingByProviderId/{providerId}")
    public Double getAverageRatingByProviderId(@PathVariable("providerId") Long providerId){
        return providerService.getAverageRatingByProviderId(providerId);
    }
    @GetMapping("/getMostRatedProviders")
    public List<Object[]> getMostRatedProviders(){
        return providerService.getMostRatedProviders();
    }
}
