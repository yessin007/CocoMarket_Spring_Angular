package com.example.coco_spring.Service.AfterSaleServices;

import com.example.coco_spring.Entity.AfterSaleServices;
import com.example.coco_spring.Repository.AfterSaleServicesRepository;
import com.example.coco_spring.Service.ICRUDService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ASSService implements ICRUDService<AfterSaleServices, Long> {
   AfterSaleServicesRepository afterSaleServicesRepository ;
    @Override
    public List<AfterSaleServices> findAll() {
        return afterSaleServicesRepository.findAll();
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
    public AfterSaleServices update(AfterSaleServices ass1, Long serviceId) throws Exception {
        if(afterSaleServicesRepository.findById(serviceId).isPresent()){

            AfterSaleServices afterSaleServicesToUpdate = afterSaleServicesRepository.findById(serviceId).get();
            afterSaleServicesToUpdate.setDescription(ass1.getDescription());
            afterSaleServicesToUpdate.setStore(ass1.getStore());
            return afterSaleServicesRepository.save(afterSaleServicesToUpdate);
        }
        throw new Exception("after sale service does not exist !");
    }

}
