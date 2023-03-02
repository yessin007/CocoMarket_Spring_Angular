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
    public Map<String, Integer> getSalesStatisticsForStoreByMonth(Long storeId);
    public List<Order> findAllByStoreId(Long storeId) ;
    public List<Product> findProductsByStoreId(Long storeId) ;

    public Map<Product, Integer> getMonthlySalesByProductAndStore(Store store, int year, int month) ;


}
