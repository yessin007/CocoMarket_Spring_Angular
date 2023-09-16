package com.example.coco_spring.Service.Subsciption;


import com.example.coco_spring.Entity.Product;
import com.example.coco_spring.Entity.ProductCategory;
import com.example.coco_spring.Entity.Subscription;
import com.example.coco_spring.Entity.User;
import com.example.coco_spring.Repository.ProductRepository;
import com.example.coco_spring.Repository.ReviewRepository;
import com.example.coco_spring.Repository.SubscripttionRepository;
import com.example.coco_spring.Repository.UserRepository;
import com.example.coco_spring.Service.Review.ReviewServices;
import com.example.coco_spring.Service.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubsciptionService {
    ProductRepository productRepository;

    SubscripttionRepository subscripttionRepository;
    UserRepository userRepository;
    UserService userService;
    ReviewServices reviewServices;

    public Map<ProductCategory,List<Product>> top10RatedProductByCategory(){
        List<Product> allProducts=productRepository.findAll();
        List<List<Product>> topChosen=new ArrayList<>();
        Map<ProductCategory,List<Product>> topRatedProductByCategoryMap=new HashMap<>();
        List<Product> colthingAndApparelProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.fashion)).sorted((b,a)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(10).collect(Collectors.toList());
        topChosen.add(colthingAndApparelProducts);
        List<Product> electronicsProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.electronics)).sorted((b,a)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(10).collect(Collectors.toList());
        topChosen.add(electronicsProducts);
        for(List<Product> productList:topChosen){
            if(!productList.isEmpty()){
                topRatedProductByCategoryMap.put(productList.get(0).getProductCategory(),productList);
            }

        }
        return topRatedProductByCategoryMap;
    }
    public List<Product> premiumProducts(){
        List<Product> allProducts=productRepository.findAll();
        List<List<Product>> topChosen=new ArrayList<>();
        Map<ProductCategory,List<Product>> topRatedProductByCategoryMap=new HashMap<>();
        List<Product> colthingAndApparelProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.fashion)).sorted((b,a)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(5).collect(Collectors.toList());
        topChosen.add(colthingAndApparelProducts);
        List<Product> electronicsProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.electronics)).sorted((b,a)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(5).collect(Collectors.toList());
        topChosen.add(electronicsProducts);
        colthingAndApparelProducts.addAll(electronicsProducts);
        return  colthingAndApparelProducts;
    }
    public Map<ProductCategory,List<Product>> getUserWishlist(Long userId){
        Map<ProductCategory,List<Product>> top10RatedProductByCategory=this.top10RatedProductByCategory();
        List<String> interestsOfBuyers=userService.findtheinterestsofbuyers(userId);
        Map<ProductCategory,List<Product>> getUserWishlistMap=new HashMap<>();
        for (ProductCategory productCategory:top10RatedProductByCategory.keySet()){
            for (String pc:interestsOfBuyers)
                if(String.valueOf(productCategory).equals(pc)){
                    getUserWishlistMap.put(productCategory,top10RatedProductByCategory.get(productCategory));
                }
        }
        return getUserWishlistMap;
    }

    public Subscription subscribeToProduct(Long userId,Long productId,int months){
        User user=userRepository.getReferenceById(userId);
        Product product=productRepository.getReferenceById(productId);
        Date dateOfCreation=new Date();
        Subscription subscription=new Subscription();
        subscription.setProduct(product);
        subscription.setRemainingDaysINMonth(30);
        subscription.setUser(user);
        subscription.setDateOfSubCreation(dateOfCreation);
        subscription.setSubMonths(months);
        //subscripttionRepository.save(subscription);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(subscription.getDateOfSubCreation());
        calendar.add(Calendar.MONTH, months);
        Date endDate = calendar.getTime();
        subscription.setDateEndOfSubscription(endDate);
        return subscripttionRepository.save(subscription);
    }

    public void updateSubscriptionEndDate(Subscription subscription) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(subscription.getDateOfSubCreation());
        calendar.add(Calendar.MONTH, subscription.getSubMonths());
        Date endDate = calendar.getTime();
        subscription.setDateEndOfSubscription(endDate);
        subscripttionRepository.save(subscription);
    }
    public Product getPrize(Long subId){
        Subscription subscription = subscripttionRepository.findById(subId).get();
        float priceMax=subscription.getProduct().getPrice();
        int subbedMonths=subscription.getSubMonths();
        Map<ProductCategory,List<Product>> top10RatedProductByCategory=this.top10RatedProductByCategory();
        if(subbedMonths == 6){
            List<Product> prizes = productRepository.findAll()
                    .stream()
                    .filter(p -> p.getPrice() > 0 && p.getPrice() < priceMax*0.15 && p.getProductCategory()==subscription.getProduct().getProductCategory())
                    .toList();
            System.out.println(prizes);
            Random rand = new Random();
            Product prize=prizes.get(rand.nextInt(prizes.size()));
            subscription.setPrize(prize);
            subscripttionRepository.save(subscription);
            return prize;
        }
        else{
            List<Product> prizes = productRepository.findAll()
                    .stream()
                    .filter(p -> p.getPrice() > 0 && p.getPrice() < priceMax*0.05 && p.getProductCategory()==subscription.getProduct().getProductCategory())
                    .toList();
            System.out.println(prizes);
            Random rand = new Random();
            Product prize=prizes.get(rand.nextInt(prizes.size()));
            subscription.setPrize(prize);
            subscripttionRepository.save(subscription);
            return prize;
        }
    }
}
