package com.example.coco_spring.Service.Store;

import com.example.coco_spring.Entity.Store;

import java.util.List;

public interface IMPCocoService {
    public Store findStoreByName(String storeName);
    public void AffectProductToStore(Long storId ,Long productId);

}
