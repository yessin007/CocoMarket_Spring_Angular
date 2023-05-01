package com.example.coco_spring.Controller.Subscription;


import com.example.coco_spring.Entity.Product;
import com.example.coco_spring.Entity.ProductCategory;
import com.example.coco_spring.Entity.Subscription;
import com.example.coco_spring.Entity.User;
import com.example.coco_spring.Repository.ProductRepository;
import com.example.coco_spring.Repository.SubscripttionRepository;
import com.example.coco_spring.Repository.UserRepository;
import com.example.coco_spring.Service.Subsciption.SubsciptionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/sub")
@CrossOrigin("*")
public class SubscriptionController {
    SubsciptionService subsciptionService;
    SubscripttionRepository subscripttionRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    @GetMapping("/viewtop10prodsbycategories")
    public Map<ProductCategory, List<Product>> top10RatedProductByCategory(){
        return subsciptionService.top10RatedProductByCategory();
    }
    @GetMapping("/mywishlist/{userId}")
    public Map<ProductCategory,List<Product>> getUserWishlist(@PathVariable("userId") Long userId){
        return  subsciptionService.getUserWishlist(userId);
    }
    @PostMapping("subscribe/{userid}/{productid}/subbedmonths/{months}")
    public Subscription subscribeToProduct(@PathVariable("userid") Long userId,@PathVariable("productid") Long productId,@PathVariable("months") int months){
        return subsciptionService.subscribeToProduct(userId,productId,months);
    }
    @GetMapping("/findsub/{userId}/{productId}")
    public Subscription findSubByProductIdUserId(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId){
        return subscripttionRepository.findByUser_IdAndProduct_ProductId(userId,productId);
    }
    @PostMapping("/updatedateendsub")
    public void updateDateEndSub(@RequestBody Subscription subscription){
         this.subsciptionService.updateSubscriptionEndDate(subscription);
    }
    @GetMapping("/winprize/{subId}")
    public Product winPrize(@PathVariable("subId") Long subId){
        return this.subsciptionService.getPrize(subId);
    }
    @DeleteMapping("/deletesub/{subId}/{userId}")
    public  void deleteSubById(@PathVariable("subId") Long subId,@PathVariable("userId") Long userId){
        Subscription subscription = subscripttionRepository.findById(subId).get();
        User user = userRepository.getReferenceById(userId);
        Product product= subscription.getPrize();
       // Product product = productRepository.getReferenceById(prodId);
        user.getSubscriptions().remove(subscription);
       // product.getSubscription()
        this.subscripttionRepository.delete(subscription);
        this.productRepository.save(product);
    }
}
