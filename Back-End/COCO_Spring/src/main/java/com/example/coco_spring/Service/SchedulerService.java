package com.example.coco_spring.Service;

import com.example.coco_spring.Entity.Product;
import com.example.coco_spring.Entity.ProductCategory;
import com.example.coco_spring.Entity.User;
import com.example.coco_spring.Repository.UserRepository;
import com.example.coco_spring.Service.Product.ProductServices;
import com.example.coco_spring.Service.User.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class SchedulerService {
    UserService userService;
    ProductServices productServices;
    EmailService emailService;
    UserRepository userRepository;

    @Scheduled(cron = "0 30 9 * * ?")
    public void runScheduledDailyOffre() throws MessagingException {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            ProductCategory cat = productServices.TopProductCategoryByUserThisWeek(user);
            List<Product> p = productServices.findTop4ProductsByCategoryOrderByRecentlyAdded(cat);
            emailService.sendDailyOfferEmail(user,p);
        }
    }
    /*@Scheduled(cron = "10 * * * * ?")
    public void runScheduledDailyOffre2() throws MessagingException {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            ProductCategory cat = productServices.TopProductCategoryByUserThisWeek(user);
            List<Product> p = productServices.findTop4ProductsByCategoryOrderByRecentlyAdded(cat);
            emailService.sendDailyOfferEmail(user,p);
        }
    }*/
    @Scheduled(cron = "0 1 0 * * ?")
    public void unblockUser(){
        List<User> expiredUsers = userRepository.findExpiredUsers();
        Date currentDate = new Date();
        for (User user : expiredUsers) {
            if (user.getDateToUnexired().before(currentDate)) {
                user.setExpired(false);
            }
        }
    }
}
