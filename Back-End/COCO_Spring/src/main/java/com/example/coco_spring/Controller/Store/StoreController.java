package com.example.coco_spring.Controller.Store;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.Product.ProductServices;
import com.example.coco_spring.Service.Store.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/store")
public class StoreController {
    StoreService storeService ;
    ProductServices productServices ;
    @PostMapping("/addStore")
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

    @DeleteMapping("/deleteStore/{storeId}")
    public void delete(@PathVariable("storeId") Long storeId){
        storeService.delete(storeId);
    }
    @GetMapping("/findStoreByName/{storeName}")
    public Store findStoreByName(@PathVariable("storeName") String storeName){
        return storeService.findStoreByName(storeName);
    }

    @GetMapping("/getStore/{storeId}")
    public Store findStore(@PathVariable("storeId") Long storeId,String storeName) {
        return storeService.findStoreByName(storeName);
    }
    @PutMapping("/affectproducttostore/{ids}/{idp}")
    public void AffectProductToStore(@PathVariable("ids") Long storeId,@PathVariable("idp")  Long productId){
        storeService.AffectProductToStore(storeId,productId);
    }
    @PutMapping("/getProductsByStore/{storeId}")
    public List<Product> getProductsByStore(@PathVariable("storeId") Long storeId) {
        return storeService.getProductsByStore(storeId);
        //return "test";
    }
    @PostMapping("/add-Bad-word")
    @ResponseBody
    public BadWords addPost_affectedto_User(@RequestBody BadWords b) {

        return storeService.addBadWord(b);
    }

 
}
