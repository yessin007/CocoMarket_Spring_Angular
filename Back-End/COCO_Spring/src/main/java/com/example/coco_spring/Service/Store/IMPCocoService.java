package com.example.coco_spring.Service.Store;

import com.example.coco_spring.Entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IMPCocoService {
    public Store findStoreByName(String storeName);

    public void AffectProductToStore(Long storId ,Long productId);
    public List<Product> getProductsByStore(Long storeId);

    List<PostStore> findAllS();

    public int Filtrage_bad_word(String ch) ;
    public ResponseEntity<?> addComment_to_Post(PostComment postComment, Long idPost, Long idUser);
    public ResponseEntity<?> addPost(PostStore post, Long id);
    public PostLike addLike_to_Post(PostLike postLike, Long idPost, Long id) ;
    //public ResponseEntity<?> addressMapss(Long  idEvent) throws IOException, InterruptedException;
    public ResponseEntity<Map<String, Object>> setLatLngToStore(Long storeId);
}
