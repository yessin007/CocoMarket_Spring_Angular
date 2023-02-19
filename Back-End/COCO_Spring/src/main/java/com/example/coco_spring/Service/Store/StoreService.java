package com.example.coco_spring.Service.Store;

import com.example.coco_spring.Entity.Store;
import com.example.coco_spring.Repository.StoreRepository;
import com.example.coco_spring.Service.ICRUDService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class StoreService implements ICRUDService<Store,Long> , IMPCocoService {
    StoreRepository storeRepository;
    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store add(Store store) {
        storeRepository.save(store);
        return null;
    }

    @Override
    public void delete(Long storeId) {
 storeRepository.deleteById(storeId);
    }

    @Override
    public Store update(Store store, Long storeId) throws Exception  {

            if(storeRepository.findById(storeId).isPresent()){

                Store storeToUpdate = storeRepository.findById(storeId).get();
                storeToUpdate.setStoreName(store.getStoreName());
                storeToUpdate.setStoreLocation(store.getStoreLocation());
                storeToUpdate.setContactInformation(store.getContactInformation());
                storeToUpdate.setStoreDescription(store.getStoreDescription());
                storeToUpdate.setCategory(store.getCategory());
                storeToUpdate.setStoreEmailAddress(store.getStoreEmailAddress());
                return storeRepository.save(storeToUpdate);
            }
            throw new Exception("store does not exist !");
        }

    @Override
    public Store findStoreByName(String storeName) {
        return storeRepository.findBystoreName(storeName);
    }
}
