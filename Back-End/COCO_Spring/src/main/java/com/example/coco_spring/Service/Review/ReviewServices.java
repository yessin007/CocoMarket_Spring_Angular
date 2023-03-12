package com.example.coco_spring.Service.Review;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.LikeDislikeRepository;
import com.example.coco_spring.Repository.ProductRepository;
import com.example.coco_spring.Repository.ReviewRepository;
import com.example.coco_spring.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ReviewServices implements IReviewServices {
    ProductRepository productRepository;
    ReviewRepository reviewRepository;
    LikeDislikeRepository likeDislikeRepository;
    UserRepository userRepository;
    @Transactional
    public Review affectReviewToProduct(Long productId,Review review){
        Product product=productRepository.findById(productId).get();
        product.getReviews().add(review);
        productRepository.save(product);
        return reviewRepository.save(review);
    }
    @Transactional
    public void userLikesProduct (Long productId, Long userId){
        User user=userRepository.findById(userId).get();
        Product product=productRepository.findById(productId).get();
        List<LikeDislikeProduct> likeDislikeProductList=product.getLikeDislikeProducts();
        if(user.getLikeDislikeProduct()!=null){
            for(LikeDislikeProduct ldp:likeDislikeProductList){
                if(ldp.getId()==user.getLikeDislikeProduct().getId()){
                    likeDislikeProductList.remove(ldp);
                    likeDislikeRepository.delete(ldp);
                }
            }
            product.setLikeDislikeProducts(likeDislikeProductList);
            productRepository.save(product);

        }
        LikeDislikeProduct likeDislikeProduct=new LikeDislikeProduct();
        likeDislikeProduct.setProductRate(ProductRate.LIKE);
        user.setLikeDislikeProduct(likeDislikeProduct);
        product.getLikeDislikeProducts().add(likeDislikeProduct);
        likeDislikeRepository.save(likeDislikeProduct);
        userRepository.save(user);
        productRepository.save(product);
    }
    @Transactional
    public void userDislikesProduct (Long productId, Long userId){
        Product product=productRepository.findById(productId).get();
        User user=userRepository.findById(userId).get();
        List<LikeDislikeProduct> likeDislikeProductList=product.getLikeDislikeProducts();
        if(user.getLikeDislikeProduct()!=null){
            for(LikeDislikeProduct ldp:likeDislikeProductList){
                if(ldp.getId()==user.getLikeDislikeProduct().getId()){
                    likeDislikeProductList.remove(ldp);
                    likeDislikeRepository.delete(ldp);
                }
            }
            product.setLikeDislikeProducts(likeDislikeProductList);
            productRepository.save(product);z
        }
        LikeDislikeProduct likeDislikeProduct=new LikeDislikeProduct();
        likeDislikeProduct.setProductRate(ProductRate.DISLIKE);
        user.setLikeDislikeProduct(likeDislikeProduct);
        product.getLikeDislikeProducts().add(likeDislikeProduct);
        likeDislikeRepository.save(likeDislikeProduct);
        userRepository.save(user);
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
