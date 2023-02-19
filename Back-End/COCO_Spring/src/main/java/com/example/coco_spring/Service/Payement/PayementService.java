package com.example.coco_spring.Service.Payement;
import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.*;
import com.example.coco_spring.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PayementService implements ICRUDService<Payement,Long> , IPayementService {

    PayementRepository payementRepository;

    @Override
    public List<Payement> findAll() {
        return payementRepository.findAll();
    }

    @Override
    public Payement retrieveItem(Long idItem) {
        return payementRepository.findById(idItem).get();
    }

    @Override
    public Payement add(Payement payement) {
        return payementRepository.save(payement);
    }

    @Override
    public void delete(Long payementId) {

        payementRepository.deleteById(payementId);

    }

    @Override
    public Payement update(Payement payement) {
        return payementRepository.save(payement);
    }
}
