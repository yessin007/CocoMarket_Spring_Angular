package com.example.coco_spring.Controller.StoreCatalog;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.Store.StoreService;
import com.example.coco_spring.Service.StoreCatalog.StoreCatalogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/storecatalog")
public class StoreCatalogController {

    StoreCatalogService storeCatalogService;



    @PostMapping("/addStoreCatalog")
    public StoreCatalog add(@RequestBody StoreCatalog class1){
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


}
