package com.example.coco_spring.Service.StoreCatalog;

import com.example.coco_spring.Entity.StoreCatalog;

import java.util.Date;
import java.util.Optional;

public interface IStoreCatalogService {
    void affecterStoreCatalogAStore(Long catalogId,Long storeId);

    public StoreCatalog findStoreCatalogByName(String catalogName);

    public StoreCatalog findStoreCatalogByDescription(String description);



    public  StoreCatalog add1(String catalogName, String catalogDescription, Date date);

}
