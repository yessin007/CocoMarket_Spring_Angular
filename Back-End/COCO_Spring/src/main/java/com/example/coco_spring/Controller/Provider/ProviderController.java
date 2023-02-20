package com.example.coco_spring.Controller.Provider;

import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Service.Provider.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
}
