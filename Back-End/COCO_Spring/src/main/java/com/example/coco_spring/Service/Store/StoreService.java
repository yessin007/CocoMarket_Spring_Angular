package com.example.coco_spring.Service.Store;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class StoreService implements ICRUDService<Store,Long> , IMPCocoService {
    StoreRepository storeRepository;
    ProductRepository productRepository;
    OrderRepository orderRepository;
    CartRepsitory cartRepsitory;
    @Override
    public List<Store> findAll() {

        return storeRepository.findAll();
    }

    @Override
    public Store retrieveItem(Long idItem) {
        return storeRepository.findById(idItem).get();
    }

    @Override
    public Store add(Store store) {

        return storeRepository.save(store);
    }

    @Override
    public void delete(Long storeId) {

        storeRepository.deleteById(storeId);
    }

    @Override
    public Store update(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store findStoreByName(String storeName) {
        return storeRepository.findBystoreName(storeName);
    }

    @Override
    public void AffectProductToStore(Long storId, Long productId) {
        Store store = storeRepository.findById(storId).get();
        Product product =productRepository.findById(productId).get();
        store.getProducts().add(product);
        storeRepository.save(store);
    }

    @Override
    public List<Product> getProductsByStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        return store.getProducts();
    }



}
