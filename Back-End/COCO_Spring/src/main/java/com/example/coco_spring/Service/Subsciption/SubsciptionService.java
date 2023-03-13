package com.example.coco_spring.Service.Subsciption;


import com.example.coco_spring.Entity.Product;
import com.example.coco_spring.Entity.ProductCategory;
import com.example.coco_spring.Repository.ProductRepository;
import com.example.coco_spring.Repository.ReviewRepository;
import com.example.coco_spring.Service.Review.ReviewServices;
import com.example.coco_spring.Service.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubsciptionService {
    ProductRepository productRepository;
    UserService userService;
    ReviewServices reviewServices;

    public Map<ProductCategory,List<Product>> top10RatedProductByCategory(){
        List<Product> allProducts=productRepository.findAll();
        List<List<Product>> topChosen=new ArrayList<>();
        Map<ProductCategory,List<Product>> topRatedProductByCategoryMap=new HashMap<>();
        List<Product> colthingAndApparelProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.ClothingAndApparel)).sorted((a,b)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(10).toList();
        topChosen.add(colthingAndApparelProducts);
        List<Product> homeGoodsProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.HomeGoods)).sorted((a,b)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(10).toList();
        topChosen.add(homeGoodsProducts);
        List<Product> electronicsProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.Electronics)).sorted((a,b)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(10).toList();
        topChosen.add(electronicsProducts);
        List<Product> beautyAndPersonalCareProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.BeautyAndPersonalCare)).sorted((a,b)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(10).toList();
        topChosen.add(beautyAndPersonalCareProducts);
        List<Product> sportingGoodsProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.SportingGoods)).sorted((a,b)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(10).toList();
        topChosen.add(sportingGoodsProducts);
        List<Product> petSuppliesProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.PetSupplies)).sorted((a,b)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(10).toList();
        topChosen.add(petSuppliesProducts);
        List<Product> servicesProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.Services)).sorted((a,b)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(10).toList();
        topChosen.add(servicesProducts);
        List<Product> foodGroceryProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.FoodAndGrocery)).sorted((a,b)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(10).toList();
        topChosen.add(foodGroceryProducts);
        List<Product> industrialProducts= allProducts.stream().filter(p -> p.getProductCategory().equals(ProductCategory.Industrial)).sorted((a,b)->reviewServices.numberOfLikes(a.getProductId())-reviewServices.numberOfLikes(b.getProductId())).limit(10).toList();
        topChosen.add(industrialProducts);
        for(List<Product> productList:topChosen){
            if(!productList.isEmpty()){
                topRatedProductByCategoryMap.put(productList.get(0).getProductCategory(),productList);
            }

        }
        return topRatedProductByCategoryMap;
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


}
