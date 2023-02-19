package com.example.coco_spring.Controller.Store;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.Store.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/api/store")

public class StoreController {
    StoreService storeService ;
    @PostMapping("/add")
    public Store add(@RequestBody Store store)  {

        return storeService.add(store);
    }

    @GetMapping("/get_all_Stores")
    public List<Store> findAll() {
        return storeService.findAll();
    }
    @PutMapping("/updateStore")

    public Store update (@RequestBody Store store)  {
        return storeService.update(store);
    }

    @DeleteMapping("/delete/{storeId}")
    public void delete(@PathVariable("storeId") Long storeId){
        storeService.delete(storeId);
    }
    @GetMapping("/findByName/{storeName}")
    public Store findStoreByName(@PathVariable("storeName") String storeName){
        return storeService.findStoreByName(storeName);
    }

    @GetMapping("/getStore/{storeId}")
    public Store findStore(@PathVariable("storeId") Long storeId) {
        return storeService.retrieveItem(storeId);
    }
}
