package com.example.coco_spring.Controller.Provider;

import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Entity.ProviderRating;
import com.example.coco_spring.Repository.ProviderRepository;
import com.example.coco_spring.Service.Provider.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/provider/")
@CrossOrigin("*")

public class ProviderController {
    ProviderRepository providerRepository;
    ProviderService providerService;
    @GetMapping("/getAllProviders")
    public List<Provider> retrieveDeliveryList(){

        return providerRepository.findAll();
    }

    @GetMapping("/retrive_provider/{providerId}")
    public Provider retrieveProvider(@PathVariable("providerId") Long providerId){

        return providerService.retrieveItem(providerId);
    }

    @PostMapping("/add-provider")
    public Provider addProvider(@RequestBody Provider provider){

        return providerRepository.save(provider);
    }

    @PutMapping("/update_provider")
    public Provider updateProvider(@RequestBody Provider provider){

        return providerService.update(provider);
    }
    @GetMapping("/getProviderDetails/{id}")
    public Provider getProviderDeatails (@PathVariable("id") Long id){
        return providerRepository.findById(id).get();
    }


    @DeleteMapping("/deleteProvider/{providerId}")
    public void deleteProvider(@PathVariable("providerId") Long providerId){

        providerRepository.deleteById(providerId);
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
