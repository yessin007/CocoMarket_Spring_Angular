package com.example.coco_spring.Service.StoreCatalog;


import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class StoreCatalogService implements ICRUDService<StoreCatalog,Long>,IStoreCatalogService {

    StoreCatalogRepository storeCatalogRepository;

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
}
