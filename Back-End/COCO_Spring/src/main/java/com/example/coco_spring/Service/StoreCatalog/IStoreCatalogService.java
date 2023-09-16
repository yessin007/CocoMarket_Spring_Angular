package com.example.coco_spring.Service.StoreCatalog;

import com.example.coco_spring.Entity.StoreCatalog;

import java.util.Date;

public interface IStoreCatalogService {
    void affecterStoreCatalogAStore(Long catalogId,Long storeId);

    public StoreCatalog findStoreCatalogByName(String catalogName);

    public StoreCatalog findStoreCatalogByDescription(String description);



    public  StoreCatalog add1(String catalogName, String catalogDescription, Date date);

    public void sendEmail(String toEmail,String subject, String body);


}
