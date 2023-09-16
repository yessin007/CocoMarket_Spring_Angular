package com.example.coco_spring.Controller.Review;


import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.ProductRepository;
import com.example.coco_spring.Repository.ReviewRepository;
import com.example.coco_spring.Service.Review.ReviewServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    ReviewServices reviewServices;
    ProductRepository productRepository;
    ReviewRepository reviewRepository;

    @PostMapping("/affectreviewtoproduct/{idUser}/{idprod}")
    public Review affectReviewToProduct(@PathVariable("idUser") Long idUser, @PathVariable("idprod") Long productId,@RequestBody Review review){
            return reviewServices.affectReviewToProduct(idUser,productId,review);
    }

    @GetMapping("/getallreviews/{idprod}")
    public List<Review> getReviewsOfProduct(@PathVariable("idprod") Long idprod){
        Product product = productRepository.findById(idprod).get();
        List<Review> reviews= new ArrayList<>();
        for(Review review:reviewRepository.findAll()){
            if (review.getProduct().getProductId() == idprod) {
                reviews.add(review);
            }
        }
        return reviews;
    }
    @PostMapping("{userId}/like/{idprod}")
    public void likeProduct (@PathVariable("idprod") Long productId,@PathVariable("userId") Long userId){
        reviewServices.userLikesProduct(productId,userId);
    }
    @PostMapping("{userId}/dislike/{idprod}")
    public void dislikeProduct (@PathVariable("idprod") Long productId,@PathVariable("userId") Long userId){
        reviewServices.userDislikesProduct(productId,userId);
    }
    @PostMapping("{userId}/likerev/{idrev}")
    public void likeReview (@PathVariable("idrev") Long reviewId,@PathVariable("userId") Long userId){
        reviewServices.userLikesReview(reviewId,userId);
    }
    @PostMapping("{userId}/dislikerev/{idrev}")
    public void dislikeReview (@PathVariable("idrev") Long reviewId,@PathVariable("userId") Long userId){
        reviewServices.userDislikesReview(reviewId,userId);
    }
    @GetMapping("/getnbrlikes/{idprod}")
    public int numberOfLikes(@PathVariable("idprod") Long productId){
        return reviewServices.numberOfLikes(productId);
    }
    @GetMapping("/getnbrdislikes/{idprod}")
    public int numberOfDislikes(@PathVariable("idprod") Long productId){
        return reviewServices.numberOfDisikes(productId);
    }
    @GetMapping("/getnbrlikesrev/{idrev}")
    public int numberOfLikesOfReview(@PathVariable("idrev") Long reviewId){
        return reviewServices.numberOfLikesofReview(reviewId);
    }
    @GetMapping("/getnbrdislikesrev/{idrev}")
    public int numberOfDislikesOfReview(@PathVariable("idrev") Long reviewId){
        return reviewServices.numberOfLikesofReview(reviewId);
    }
    @GetMapping("/getuserbyreview/{idrev}")
    public User getUserByReview(@PathVariable("idrev") Long idRev){
        return reviewServices.getUserByReview(idRev);
    }

}
