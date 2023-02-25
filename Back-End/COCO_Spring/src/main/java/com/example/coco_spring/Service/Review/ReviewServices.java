package com.example.coco_spring.Service.Review;

import com.example.coco_spring.Entity.LikeDislikeProduct;
import com.example.coco_spring.Entity.Product;
import com.example.coco_spring.Entity.ProductRate;
import com.example.coco_spring.Entity.Review;
import com.example.coco_spring.Repository.LikeDislikeInterface;
import com.example.coco_spring.Repository.ProductRepository;
import com.example.coco_spring.Repository.ReviewRepository;
import com.example.coco_spring.Service.Review.IReviewServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ReviewServices implements IReviewServices {
    ProductRepository productRepository;
    ReviewRepository reviewRepository;
    LikeDislikeInterface likeDislikeInterface;
    @Transactional
    public Review affectReviewToProduct(Long productId,Review review){
        Product product=productRepository.findById(productId).get();
        product.getReviews().add(review);
        productRepository.save(product);
        return reviewRepository.save(review);
    }
    @Transactional
    public void LikeDislikeProduct (Long productId, LikeDislikeProduct likeDislikeProduct){
        Product product=productRepository.findById(productId).get();
        product.getLikeDislikeProducts().add(likeDislikeProduct);
        likeDislikeInterface.save(likeDislikeProduct);
        productRepository.save(product);
    }
    public int numberOfLikes(Long productId){
        Product product=productRepository.findById(productId).get();
        int likes=0;
        for (LikeDislikeProduct e:product.getLikeDislikeProducts()){
            if(e.getProductRate().equals(ProductRate.LIKE)){
                likes++;
            }
        }
        return  likes;
    }
    public int numberOfDisikes(Long productId){
        Product product=productRepository.findById(productId).get();
        int dislikes=0;
        for (LikeDislikeProduct e:product.getLikeDislikeProducts()){
            if(e.getProductRate().equals(ProductRate.DISLIKE)){
                dislikes++;
            }
        }
        return  dislikes;
    }
}
