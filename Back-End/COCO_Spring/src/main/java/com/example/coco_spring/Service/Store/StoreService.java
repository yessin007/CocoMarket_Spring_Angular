package com.example.coco_spring.Service.Store;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StoreService implements ICRUDService<Store,Long> , IMPCocoService {
    StoreRepository storeRepository;
    ProductRepository productRepository;
    OrderRepository orderRepository;
    CartRepsitory cartRepsitory;
    BadWordRepo badWordRepo ;
    UserRepository userRepo;
    PostRepo postRepo ;
    UserDataLoadRepo UserDataLoadRepo;
    CategoryAdverRepo categoryAdverRepo ;
    PostCommentRepo postCommentRepo;
    @Override
    public List<Store> findAll() {

        return storeRepository.findAll();
    }

    @Override
    public Store retrieveItem(Long idItem) {
        return storeRepository.findById(idItem).get();
    }

    @Override
    public Store add(Store store) {

        return storeRepository.save(store);
    }

    @Override
    public void delete(Long storeId) {

        storeRepository.deleteById(storeId);
    }

    @Override
    public Store update(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store findStoreByName(String storeName) {
        return storeRepository.findBystoreName(storeName);
    }

    @Override
    public void AffectProductToStore(Long storId, Long productId) {
        Store store = storeRepository.findById(storId).get();
        Product product =productRepository.findById(productId).get();
        store.getProducts().add(product);
        storeRepository.save(store);
    }

    @Override
    public List<Product> getProductsByStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow();
        return store.getProducts();
    }


    public BadWords addBadWord(BadWords b ) {

    public Store getStoreByProductId(Long productId){
        List<Store> stores= storeRepository.findAll();
        Product product=productRepository.findById(productId).get();
        for(Store store:stores){
            for(Product p: store.getProducts()){
                if(p.equals(product)){
                    return store;
                }
            }
        }
        return null;
    }


        return badWordRepo.save(b);
    }
    public ResponseEntity<?> addPost(PostStore post, Long IdUser) {

        User u = userRepo.findById(IdUser).orElse(null);
        DetctaDataLoad(post.getBody(),IdUser);
        DetctaDataLoad(post.getPostTitle(),IdUser);
        if (Filtrage_bad_word(post.getBody()) == 0 && Filtrage_bad_word(post.getPostTitle()) == 0) {
            post.setUser(u);
            u.getPostStores().add(post);
            postRepo.save(post);
            return ResponseEntity.ok().body(post);
        } else
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Bads Word Detected");
    }
    public int Filtrage_bad_word(String ch) {
        int x = 0;
        List<BadWords> l1 = (List<BadWords>) badWordRepo.findAll();
        for (BadWords badWord : l1) {
            // if (badWord.getWord().contains(ch))
            if (ch.contains(badWord.getWord()) == true)
                x = 1;
        }
        return x;

    }
    public void DetctaDataLoad (String ch , Long idUser) {

        List<UserDataLoad> ul = UserDataLoadRepo.findAll();
        User u = userRepo.findById(idUser).orElse(null);
        for (CategoryAdve string : categoryAdverRepo.findAll()) {
            if (ch.contains(string.getNameCategory())) {
                if (existDataForUser(string.getNameCategory(),idUser) == true) {
                    UserDataLoad l = getData(string.getNameCategory(),idUser);
                    l.setNbrsRequet(l.getNbrsRequet()+1);
                    UserDataLoadRepo.save(l);
                }
                else {
                    UserDataLoad l1 = new UserDataLoad();
                    l1.setCategorieData(string.getNameCategory());
                    l1.setUser(u);
                    l1.setNbrsRequet(1);
                    UserDataLoadRepo.save(l1);

                }
            }
        }
    }
    public Boolean existDataForUser(String ch,Long IdUser) {
        Boolean x = false;
        for (UserDataLoad userDataLoad : UserDataLoadRepo.findAll()) {
            if (userDataLoad.getCategorieData().equals(ch) && userDataLoad.getUser().getId() == IdUser) {
                x = true;
            }
        } return x;
    }
    public UserDataLoad getData(String ch,Long IdUser) {
        UserDataLoad x = null;
        for (UserDataLoad userDataLoad : UserDataLoadRepo.findAll()) {
            if (userDataLoad.getCategorieData().equals(ch) && userDataLoad.getUser().getId() == IdUser) {
                x = userDataLoad;
            }
        }
        return x;
    }
    public ResponseEntity<?> addComment_to_Post(PostComment postComment, Long idPost, Long idUser) {
        PostStore p = postRepo.findById(idPost).orElse(null);
        User u = userRepo.findById(idUser).orElse(null);
        DetctaDataLoad(postComment.getCommentBody(),idUser);
        if (Filtrage_bad_word(postComment.getCommentBody()) == 0) {
            postComment.setUser(u);
            postComment.setPostStore(p);

            postCommentRepo.save(postComment);
            return ResponseEntity.ok().body(postComment);      }else
            /*
             * Set<PostComment> pc = p.getPostComments(); pc.add(postComment);
             * p.setPostComments(pc); postRepo.save(p);
             *
             * Set<PostComment> pu = u.getPostComments(); pu.add(postComment);
             * u.setPostComments(pu); userRepo.save(u);
             *
             *
             */
            //}
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Bads Word Detected");
    }

}
