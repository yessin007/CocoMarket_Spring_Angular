package com.example.coco_spring.Service.Subsciption;

import com.example.coco_spring.Entity.Product;
import com.example.coco_spring.Entity.ProductCategory;
import com.example.coco_spring.Entity.Subscription;
import com.example.coco_spring.Repository.SubscripttionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
@AllArgsConstructor
@Slf4j
public class TimeUpdater {
    SubscripttionRepository subscripttionRepository;
    SubsciptionService subsciptionService;
    @Scheduled(cron = "* 59 23 * * *")
    public void updateSubStatus(){
        List<Subscription> subscriptions=subscripttionRepository.findAll();

        for(Subscription subscription:subscriptions){
            if (subscription.getRemainingDaysINMonth()>0){
                subscription.setRemainingDaysINMonth(subscription.getRemainingDaysINMonth()-1);
            }
            else if(subscription.getSubMonths()>0 && subscription.getRemainingDaysINMonth()==0) {
                subscription.setSubMonths(subscription.getSubMonths()-1);
            }
            else if (subscription.getRemainingDaysINMonth()==0&&subscription.getSubMonths()==0) {
                Map<ProductCategory,List<Product>> top10RatedProductByCategory=subsciptionService.top10RatedProductByCategory();
                List<Product> prizes=top10RatedProductByCategory.get(subscription.getProduct().getProductCategory()).stream().filter(p-> p.getPrice()<p.getPrice()*0.2).toList();
                Random rand = new Random();
                Product prize=prizes.get(rand.nextInt(prizes.size()));
                subscription.setPrize(prize);
            }
        }
        subscripttionRepository.saveAll(subscriptions);
    }
}
