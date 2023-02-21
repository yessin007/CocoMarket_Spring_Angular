package com.example.coco_spring.Service.Provider;

import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Repository.ProviderRepository;
import com.example.coco_spring.Service.ICRUDService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class ProviderService implements ICRUDService<Provider,Long>, IProviderService {

    ProviderRepository providerRepository;

    @Override
    public List<Provider> findAll() {

        return providerRepository.findAll();
    }

    @Override
    public Provider retrieveItem(Long providerId) {

        return providerRepository.findById(providerId).get();
    }

    @Override
    public Provider add(Provider provider) {

        return providerRepository.save(provider);
    }

    @Override
    public void delete(Long providerId) {

        providerRepository.deleteById(providerId);
    }

    @Override
    public Provider update(Provider delivery) {

        return providerRepository.save(delivery);
    }

}
