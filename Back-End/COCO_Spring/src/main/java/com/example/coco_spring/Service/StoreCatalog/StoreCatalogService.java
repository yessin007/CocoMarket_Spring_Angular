package com.example.coco_spring.Service.StoreCatalog;


import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class StoreCatalogService implements ICRUDService<StoreCatalog,Long>,IStoreCatalogService {

    StoreCatalogRepository storeCatalogRepository;
    StoreRepository storeRepository;

    @Override
    public List<StoreCatalog> findAll() {
        return storeCatalogRepository.findAll();

    }

    @Override
    public StoreCatalog retrieveItem(Long idItem) {
        return storeCatalogRepository.findById(idItem).get();
    }


    @Override
    public StoreCatalog add(StoreCatalog class1) {
        return storeCatalogRepository.save(class1);
    }

    public StoreCatalog add1( String catalogName, String catalogDescription, Date date) {
        // Perform input validation here
        if ( catalogName == null || catalogDescription == null || date == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        // Create a new StoreCatalog object with the provided attributes
        StoreCatalog newCatalog = new StoreCatalog();
        newCatalog.setCatalogName(catalogName);
        newCatalog.setCatalogDescription(catalogDescription);
        newCatalog.setDate(date);

        // Save the new StoreCatalog object using the storeCatalogRepository
        return storeCatalogRepository.save(newCatalog);
    }




    @Override
    public void delete(Long aLong) {
        storeCatalogRepository.deleteById(aLong);
    }

    @Override
    public StoreCatalog update(StoreCatalog Classe11) {
        return storeCatalogRepository.save(Classe11);
    }

    @Override
    public void affecterStoreCatalogAStore(Long catalogId, Long storeId) {
        StoreCatalog storeCatalog =storeCatalogRepository.findById(catalogId).get();
        Store store=storeRepository.findById(storeId).get();
        store.setStoreCatalog(storeCatalog);
        storeRepository.save(store);
    }

    @Override
    public StoreCatalog findStoreCatalogByName(String catalogName) {

        return storeCatalogRepository.findStoreCatalogByCatalogName(catalogName);
    }

    @Override
    public StoreCatalog findStoreCatalogByDescription(String description) {
        return storeCatalogRepository.findStoreCatalogByCatalogDescription(description);
    }

    @Override
    public Optional<StoreCatalog> findStoreCatalogByDate(Date date) {

        return Optional.ofNullable(storeCatalogRepository.findByDate(date));
    }
}
