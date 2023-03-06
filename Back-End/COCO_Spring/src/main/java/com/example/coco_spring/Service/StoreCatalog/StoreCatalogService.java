package com.example.coco_spring.Service.StoreCatalog;


import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
