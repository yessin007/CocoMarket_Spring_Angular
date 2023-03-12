package com.example.coco_spring.Service.AfterSaleServices;

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
public class ASSService implements ICRUDService<AfterSaleServices, Long> , IASSService{
   AfterSaleServicesRepository afterSaleServicesRepository ;
   StoreRepository storeRepository;
    @Override
    public List<AfterSaleServices> findAll() {

        return afterSaleServicesRepository.findAll();
    }

    @Override
    public AfterSaleServices retrieveItem(Long idItem) {

        return afterSaleServicesRepository.findById(idItem).get();
    }

    @Override
    public AfterSaleServices add(AfterSaleServices ass1) {

        return afterSaleServicesRepository.save(ass1);
    }

    @Override
    public void delete(Long serviceId) {

        afterSaleServicesRepository.deleteById(serviceId);
    }

    @Override
    public AfterSaleServices update(AfterSaleServices afterSaleServices) {
        return afterSaleServicesRepository.save(afterSaleServices);
    }

    public void assignerSAVaStores() {

    }
}
