package com.example.coco_spring.Service.StoreCatalog;

import com.example.coco_spring.Entity.StoreCatalog;
import com.example.coco_spring.Entity.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IStoreCatalogService {
    void affecterStoreCatalogAStore(Long catalogId,Long storeId);

    public StoreCatalog findStoreCatalogByName(String catalogName);

    public StoreCatalog findStoreCatalogByDescription(String description);

    public Optional<StoreCatalog> findStoreCatalogByDate(Date date);

    public  StoreCatalog add1(String catalogName, String catalogDescription, Date date);


}
