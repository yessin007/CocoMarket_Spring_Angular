package com.example.coco_spring.Service.AfterSaleServices;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ASSService implements ICRUDService<AfterSaleServices, Long> , IASSService{
   AfterSaleServicesRepository afterSaleServicesRepository ;
    @Override
    public List<AfterSaleServices> findAll() {
        return afterSaleServicesRepository.findAll();
    }

    @Override
    public AfterSaleServices retrieveItem(Long idItem) {
        return null;
    }

    @Override
    public AfterSaleServices add(AfterSaleServices ass1) {
         afterSaleServicesRepository.save(ass1);
        return null;
    }

    @Override
    public void delete(Long serviceId) {
afterSaleServicesRepository.deleteById(serviceId);
    }

    @Override
    public AfterSaleServices update(AfterSaleServices Classe2) {
        return null;
    }

}
