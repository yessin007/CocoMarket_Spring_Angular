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
	public Review affectReviewToProduct(Long userId,Long productId,Review review){
		User user=userRepository.findById(userId).get();
		Product product=productRepository.findById(productId).get();
		review.setUser(user);
		review.setProduct(product);
		reviewRepository.save(review);
		productRepository.save(product);
		userRepository.save(user);
		return review;
	}
	public User getUserByReview(Long reviewId){
		Review review = reviewRepository.findById(reviewId).get();
		for(Review rev:reviewRepository.findAll()){
			if (review.getReviewId() == rev.getReviewId()){
				return review.getUser();
			}
		}
		return null;
	}
	@Transactional
	public void userLikesProduct (Long productId, Long userId){
		User user=userRepository.findById(userId).get();
		Product product=productRepository.findById(productId).get();
		LikeDislikeProduct previousLike = likeDislikeRepository.findByProductAndUser(product, user);
		if (previousLike != null) {
			// Delete the previous like
			product.getLikeDislikeProducts().remove(previousLike);
			user.getLikeDislikeProductList().remove(previousLike);
			likeDislikeRepository.delete(previousLike);
		}
		LikeDislikeProduct likeDislikeProduct=new LikeDislikeProduct();
		likeDislikeProduct.setProductRate(ProductRate.LIKE);
		likeDislikeProduct.setProduct(product);
		likeDislikeProduct.setUser(user);
		likeDislikeRepository.save(likeDislikeProduct);
		productRepository.save(product);
	}
	@Transactional
	public void userDislikesProduct (Long productId, Long userId){
		User user=userRepository.findById(userId).get();
		Product product=productRepository.findById(productId).get();
		LikeDislikeProduct previousLike = likeDislikeRepository.findByProductAndUser(
			product, user
		);
		if (previousLike != null) {
			// Delete the previous like
			product.getLikeDislikeProducts().remove(previousLike);
			user.getLikeDislikeProductList().remove(previousLike);
			likeDislikeRepository.delete(previousLike);
		}
		LikeDislikeProduct likeDislikeProduct=new LikeDislikeProduct();
		likeDislikeProduct.setProductRate(ProductRate.DISLIKE);
		likeDislikeProduct.setProduct(product);
		likeDislikeProduct.setUser(user);
		likeDislikeRepository.save(likeDislikeProduct);
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
