package com.example.coco_spring.Controller.Store;

import com.example.coco_spring.Entity.AfterSaleServices;
import com.example.coco_spring.Entity.Store;
import com.example.coco_spring.Service.Store.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StoreController {
    StoreService storeService ;
    @PostMapping("/addStore")
    public Store add(@RequestBody Store store)  {
        Store store1 =storeService.add(store);
        return store1;
    }
    @GetMapping("/getStore")
    public List<Store> findAll() {
        return storeService.findAll();
    }
    @PostMapping("/updateStore")
    public Store update (@RequestBody Store store)  {
        return storeService.update(store);
    }

    @DeleteMapping("/deleteStore/{storeId}")
    public void delete(@PathVariable("storeId") Long storeId){
        storeService.delete(storeId);
    }
    @GetMapping("/findStoreByName/{storeName}")
    public Store findStoreByName(@PathVariable("storeName") String storeName){
        return storeService.findStoreByName(storeName);
    }
}
