package com.example.coco_spring.Controller.Review;


import com.example.coco_spring.Entity.LikeDislikeProduct;
import com.example.coco_spring.Entity.ProductRate;
import com.example.coco_spring.Entity.Review;
import com.example.coco_spring.Service.Review.ReviewServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    ReviewServices reviewServices;

    @PostMapping("/affectreviewtoproduct/{idprod}")
    public Review affectReviewToProduct(@PathVariable("idprod") Long productId,@RequestBody Review review){
            return reviewServices.affectReviewToProduct(productId,review);
    }
    @PostMapping("/affectlikedisliketo/{idprod}")
    public void LikeDislikeProduct (@PathVariable("idprod") Long productId,@RequestBody LikeDislikeProduct likeDislikeProduct){
        reviewServices.LikeDislikeProduct(productId,likeDislikeProduct);
    }
    @GetMapping("/getnbrlikes/{idprod}")
    public int numberOfLikes(@PathVariable("idprod") Long productId){
        return reviewServices.numberOfLikes(productId);
    }
    @GetMapping("/getnbrdislikes/{idprod}")
    public int numberOfDislikes(@PathVariable("idprod") Long productId){
        return reviewServices.numberOfDisikes(productId);
    }
}
