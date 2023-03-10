package com.example.coco_spring.Service.Store;

import com.example.coco_spring.Entity.Order;
import com.example.coco_spring.Entity.PostComment;
import com.example.coco_spring.Entity.Product;
import com.example.coco_spring.Entity.Store;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IMPCocoService {
    public Store findStoreByName(String storeName);
    public void AffectProductToStore(Long storId ,Long productId);
    public List<Product> getProductsByStore(Long storeId);

    public int Filtrage_bad_word(String ch) ;
    public ResponseEntity<?> addComment_to_Post(PostComment postComment, Long idPost, Long idUser);
}
