package com.example.coco_spring.Controller.StoreCatalog;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.NotificationRepository;
import com.example.coco_spring.Repository.StoreCatalogRepository;
import com.example.coco_spring.Service.StoreCatalog.StoreCatalogService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/storecatalog")
public class StoreCatalogController {

    StoreCatalogService storeCatalogService;
NotificationRepository notificationRepository ;
StoreCatalogRepository storeCatalogRepository ;


    @PostMapping("/addStoreCatalog")
    public StoreCatalog add(@RequestBody StoreCatalog class1){

        Notification notif = new Notification();
        notif.setCreatedAt(new Date());
        notif.setMessage(class1 + " new cataloq");
        notif.setRead(false);
        notificationRepository.save(notif);
        return storeCatalogService.add(class1);
    }

    @GetMapping("/get_all_StoreCatalog")
    public List<StoreCatalog> findAll(){
        return storeCatalogService.findAll();
    }

    @PutMapping("/updateStoreCatalog")
    public StoreCatalog update(@RequestBody StoreCatalog Classe1){
        return storeCatalogService.update(Classe1);
    }
    @DeleteMapping("/deleteStoreCatalog/{catalogId}")
    public void delete(@PathVariable("catalogId") Long aLong){
        storeCatalogService.delete(aLong);
    }

    @GetMapping("getById_StoreCatalog/{catalogId}")
    public StoreCatalog retrieveItem(@PathVariable("catalogId") Long idItem){
        return storeCatalogService.retrieveItem(idItem);
    }

    @PutMapping("/affecterStoreCatalogAStore/{catalogId}/{storeId}")
    public void affecterStoreCatalogAStore(@PathVariable("catalogId") Long catalogId,@PathVariable("storeId") Long storeId){
        storeCatalogService.affecterStoreCatalogAStore(catalogId,storeId);
    }

    @GetMapping("/findStoreCatalogByName/{catalogName}")
    public StoreCatalog findStoreCatalogByName(@PathVariable("catalogName") String catalogName){

        return storeCatalogService.findStoreCatalogByName(catalogName);
    }

    @GetMapping("findStoreCatalogByDescription/{description}")
    public StoreCatalog findStoreCatalogByDescription(@PathVariable("description") String description){
        return storeCatalogService.findStoreCatalogByDescription(description);
    }

    @GetMapping("findStoreCatalogByDate/{date}")
    public Optional<StoreCatalog> findStoreCatalogByDate(@PathVariable("date")Date date){
        return storeCatalogService.findStoreCatalogByDate(date);
    }


    @PostMapping("add1")
    public ResponseEntity<StoreCatalog> add1( @RequestParam String catalogName, @RequestParam String catalogDescription,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        StoreCatalog storeCatalog = storeCatalogService.add1(catalogName, catalogDescription, date);
        return new ResponseEntity<>(storeCatalog, HttpStatus.CREATED);
    }
    @GetMapping("observeProductCategory/{catalogId}/{productId}")
    public String observeProductCategory(@PathVariable("catalogId") Long catalogId,@PathVariable("productId") Long productId){
        return storeCatalogService.observeProductCategory(catalogId,productId);

    }

    }
