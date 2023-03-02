package com.example.coco_spring.Service.Store;

import com.example.coco_spring.Entity.Order;
import com.example.coco_spring.Entity.Product;
import com.example.coco_spring.Entity.Store;

import java.util.List;
import java.util.Map;

public interface IMPCocoService {
    public Store findStoreByName(String storeName);
    public void AffectProductToStore(Long storId ,Long productId);
    public List<Product> getProductsByStore(Long storeId);


}
