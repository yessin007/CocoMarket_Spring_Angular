package com.example.coco_spring.Controller.Store;

import com.example.coco_spring.Entity.AfterSaleServices;
import com.example.coco_spring.Entity.Store;
import com.example.coco_spring.Service.Store.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/Store")
public class StoreController {
    StoreService storeService ;
    @PostMapping("/add")
    public Store add(@RequestBody Store store)  {
        Store store1 =storeService.add(store);
        return store1;
    }
    @GetMapping("/get")
    public List<Store> findAll() {
        return storeService.findAll();
    }
    @PostMapping("/update")
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
}
