package com.example.coco_spring.Service.AfterSaleServices;

import com.example.coco_spring.Entity.AfterSaleServices;
import com.example.coco_spring.Entity.CustomerFeedback;
import com.example.coco_spring.Repository.CustomerFeedbackRepository;
import com.example.coco_spring.Service.ICRUDService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FeedbackService implements  IASSService{
    CustomerFeedbackRepository customerFeedbackRepository ;

    public CustomerFeedback add(CustomerFeedback fed) {

        return customerFeedbackRepository.save(fed);
    }
}
