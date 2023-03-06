package com.example.coco_spring.Service.StoreCatalog;

import com.example.coco_spring.Entity.StoreCatalog;

public interface IStoreCatalogService {
    void affecterStoreCatalogAStore(Long catalogId,Long storeId);

    public StoreCatalog findStoreCatalogByName(String catalogName);
}
