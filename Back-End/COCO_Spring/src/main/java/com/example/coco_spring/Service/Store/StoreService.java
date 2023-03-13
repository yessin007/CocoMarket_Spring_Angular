package com.example.coco_spring.Service.Store;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.HashSet;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class StoreService implements ICRUDService<Store,Long> , IMPCocoService {
    StoreRepository storeRepository;
    QuizzRepository quizzRepository;

    UserRepository userRepository;
    ProductRepository productRepository;
    OrderRepository orderRepository;
    CartRepsitory cartRepsitory;
    @Autowired
    BadWordRepo badWordRepo ;
    @Autowired
    UserRepository userRepo;
    @Autowired
    PostRepo postRepo ;
    @Autowired
    UserDataLoadRepo UserDataLoadRepo;
    @Autowired
    CategoryAdverRepo categoryAdverRepo ;
    @Autowired
    PostCommentRepo postCommentRepo;
    PostLikeRepo postLikeRepo ;
    EmailService emailService ;
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
    public ResponseEntity<?> addPost(PostStore post, Long id) {

        User u = userRepo.findById(id).orElse(null);
        DetctaDataLoad(post.getBody(),id);
        DetctaDataLoad(post.getPostTitle(),id);
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

            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Bads Word Detected");
    }
    public PostLike addLike_to_Post(PostLike postLike, Long idPost, Long id) {
        int x=0;
        boolean y =false;
        PostStore p = postRepo.findById(idPost).orElse(null);
        User u = userRepo.findById(id).orElse(null);
        for (PostLike l : postLikeRepo.findAll()) {
            if(l.getPostStore().getPostId() == idPost && l.getUser().getId() == id)
            {
                x=1;
                y=l.getIsLiked();
                postLikeRepo.delete(l);
            }

        }
        if (x ==0 || (x == 1 && y!=postLike.getIsLiked()	)) {
            DetctaDataLoad(p.getBody(),id);
            postLike.setUser(u);
            postLike.setPostStore(p);
            postLikeRepo.save(postLike);}
        return postLike;
    }

    @Override
    public ResponseEntity<?> addressMapss(Long idEvent) throws IOException, InterruptedException {

        // TODO Auto-generated method stub
        return null;
    }

    public int PostLikeFromUser(Long isUser,Long Idpost) {
        int x =0;
        for (PostLike l : postLikeRepo.findAll()) {
            if (l.getPostStore().getPostId()== Idpost && l.getUser().getId()== isUser) {
                if (l.getIsLiked() == true) {x= 1;}
                else {x=0;}
            }

        }
        return x;
    }
    public PostStore Get_best_Post() throws MessagingException {
        PostStore p1 = null;
        int x = 0;
        for (PostStore p : postRepo.findAll()) {
            if (postRepo.diffrence_entre_date(p.getCreatedAt()) <= 7) {
                if (p.getPostLikes().size() > x) {
                    p1 = p;
                    x = p.getPostLikes().size();
                }
                /*
                 * else if (p.getPostLikes().size() == x) { if
                 * (postRepo.diffrence_entre_date(p.getCreatedAt())<postRepo.
                 * diffrence_entre_date(p1.getCreatedAt())) { p1 = p;} }
                 */
            }
        }
        emailService.sendAllertReport("Congrates Your Post : "+p1.getPostTitle()+" is the best post for week  \n", p1.getUser().getEmail());
        return p1;
    }
    public PostStore Give_Etoile_Post(Long idPost, int nb_etouile) {
        PostStore post1 = postRepo.findById(idPost).orElseThrow(() -> new EntityNotFoundException("post not found"));

        post1.setNb_etoil(nb_etouile);
        return postRepo.save(post1);

    }
    public ResponseEntity<?> Report_User(Long idPost,Long id) throws MessagingException {
        PostStore post1 = postRepo.findById(idPost).orElseThrow(() -> new EntityNotFoundException("post not found"));
        int x =0;
        for (User u : post1.getReportedby()) {
            if(u.getId() == id)
                x=1;
        }
        if (x ==0) {
            User u = userRepo.findById(id).orElse(null);
            post1.setNb_Signal(post1.getNb_Signal() + 1);
            Set<User> ur = post1.getReportedby();
            ur.add(u);
            post1.setReportedby(ur);
            if (post1.getNb_Signal()>7)
                emailService.sendAllertReport("Your Post : "+post1.getPostTitle()+ " have More than "+ post1.getNb_Signal() +" reports \n", post1.getUser().getEmail());
            postRepo.save(post1);
            return ResponseEntity.status(HttpStatus.OK).body("Post : "+idPost+" reported ");
        }
        else return ResponseEntity.status(HttpStatus.OK).body("U are already report this post ");


    }

    public List<PostStore> Searchpost(String ch,Long id){
        List<PostStore> ll = new ArrayList<>();
        for (PostStore post : postRepo.findAll()) {
            if (post.getBody().contains(ch) || post.getPostTitle().contains(ch))
                ll.add(post);
        }
        DetctaDataLoad(ch,id);
        return ll;
    }


    public void createQuizz(Quiz Q, Long idCourse,Long idUser)  {
        Store c = storeRepository.findById(idCourse).get();
        User usr = userRepository.findById(idUser).get();

        Set<Quiz> quiz = new HashSet<>();
        quiz.add(Q);
        c.getQuiz().add(Q);

        storeRepository.flush();
        quizzRepository.save(Q);

    }

}
