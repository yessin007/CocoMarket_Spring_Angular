package com.example.coco_spring.Controller.Store;

import com.example.coco_spring.Entity.*;

import com.example.coco_spring.QRCode.QRCodeGenerator;

import com.example.coco_spring.Repository.StoreRepository;

import com.example.coco_spring.Service.Product.ProductServices;
import com.example.coco_spring.Service.Store.StoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.websocket.server.PathParam;
import java.io.IOException;

import java.sql.Date;
import java.time.LocalDate;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/store")
public class StoreController {
    StoreService storeService;
    StoreRepository storeRepository;
    ProductServices productServices;

    @PostMapping(value="/addStore",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Store add(@RequestPart("store") Store store,
                     @RequestPart("imageFile") MultipartFile[] file)  {

        try {
            Set<ImageSModel> images = uploadImage(file);
            store.setStoreImages(images);
            return storeService.add(store);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null ;
        }

    }
    public Set<ImageSModel> uploadImage(MultipartFile[] multipartFiles)  throws IOException {
        Set<ImageSModel> imageSModels = new HashSet<>();
        for(MultipartFile file : multipartFiles){
            ImageSModel imageSModel =new ImageSModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageSModels.add(imageSModel);
        }
        return imageSModels ;
    }
  /*  @PostMapping("/add-Post-image/{idpost}")
    @ResponseBody
    public ResponseEntity<?> addpostimage(@RequestParam MultipartFile image,@PathVariable("idpost") Long idpost) throws IOException {
        return storeService.addimagepost(image,idpost);

    }*/
    @GetMapping("/get_all_Stores")
    public List<Store> findAll() {
        return storeService.findAll();
    }

    @PutMapping("/updateStore")
    public Store update(@RequestBody Store store) {
        return storeService.update(store);
    }
    @GetMapping("/retrive_Store/{storeId}")
    public Store retrieveStore(@PathVariable("storeId") Long storeId) {
        return storeService.retrieveItem(storeId);
    }
    @DeleteMapping("/deleteStore/{storeId}")
    public void delete(@PathVariable("storeId") Long storeId) {
        storeService.delete(storeId);
    }

    @GetMapping("/findStoreByName/{storeName}")
    public Store findStoreByName(@PathVariable("storeName") String storeName) {
        return storeService.findStoreByName(storeName);
    }

    @GetMapping("/getStore/{storeId}")
    public Store findStore(@PathVariable("storeId") Long storeId, String storeName) {
        return storeService.findStoreByName(storeName);
    }


    @PutMapping("/affectproducttostore/{ids}/{idp}")
    public void AffectProductToStore(@PathVariable("ids") Long storeId, @PathVariable("idp") Long productId) {
        storeService.AffectProductToStore(storeId, productId);
    }

    @PutMapping("/getProductsByStore/{storeId}")
    public List<Product> getProductsByStore(@PathVariable("storeId") Long storeId) {
        return storeService.getProductsByStore(storeId);
        //return "test";
    }

    @PostMapping("/add-Bad-word")
    @ResponseBody
    public BadWords addPost_affectedto_User(@RequestBody BadWords b) {

        return storeService.addBadWord(b);
    }

    @PostMapping("/add-Post/{id}")
    @ResponseBody
    public ResponseEntity<?> addPost_affectedto_User(@RequestBody PostStore post, @PathVariable("id") Long id) {

        post.setCreatedAt(Date.valueOf(LocalDate.now()));
        return storeService.addPost(post, id);
    }

    @PostMapping("/add-Comment/{IdPost}/{id}")
    @ResponseBody
    public ResponseEntity<?> addComment_to_Post(@RequestBody PostComment postComment, @PathVariable("IdPost") Long IdPost, @PathVariable("id") Long id) {
        postComment.setCommentedAt(Date.valueOf(LocalDate.now()));

        return storeService.addComment_to_Post(postComment, IdPost, id);
    }

    @PostMapping("/add-Like-post/{IdPost}/{id}")
    @ResponseBody
    public PostLike addLike_to_Post(@RequestBody(required = false) PostLike postLike, @PathVariable("IdPost") Long IdPost, @PathVariable("id") Long id) {
        PostLike pos1 = new PostLike();
        pos1.setIsLiked(true);

        return storeService.addLike_to_Post(pos1, IdPost, id);
    }

    @PostMapping("/add-DisLike-post/{IdPost}/{id}")
    @ResponseBody
    public PostLike addDisLike_to_Post(@RequestBody(required = false) PostLike postLike, @PathVariable("IdPost") Long IdPost, @PathVariable("id") Long id) {
        PostLike pos1 = new PostLike();
        pos1.setIsLiked(false);

        return storeService.addLike_to_Post(pos1, IdPost, id);
    }

    //to fix
    @GetMapping("/get-user-islike-post/{IdPost}/{id}")
    @ResponseBody
    public int addDisLike_to_Post(@PathVariable("IdPost") Long IdPost, @PathVariable("id") Long id) {

        return storeService.PostLikeFromUser(IdPost, id);
    }

    @GetMapping("/Get-best-post-week")
    public String Get_best_Post() throws MessagingException {
        return storeService.Get_best_Post();
    }

    @PutMapping("/Give-post-etoile/{idPost}/{nb_etoile}")
    public PostStore Give_Etoile_Post(@PathVariable("idPost") Long idPost, @PathVariable("nb_etoile") int nb_etoile) {
        return storeService.Give_Etoile_Post(idPost, nb_etoile);
    }

    @GetMapping("/Report-Post/{idPost}/{id}")
    public ResponseEntity<?> Report_User(@PathVariable("idPost") Long idPost, @PathVariable("id") Long id) throws MessagingException {
        return storeService.Report_User(idPost, id);
    }

    @GetMapping("/Get-Search-post{ch}/{id}")
    public List<PostStore> adversting_bydata(@PathVariable("ch") String ch, @PathVariable("id") Long id) {
        return storeService.Searchpost(ch, id);
    }

    /*@GetMapping("/googleMap/{idStore}")
    public ResponseEntity<?> addressMapss(@PathVariable Long  idStore) throws IOException, InterruptedException {
        //	eventService.addressMapss(idEvent);
        return new ResponseEntity(storeService.addressMapss(idStore), HttpStatus.OK);
    }*/
    @PostMapping("/AssignLocationtoStore/{locationId}/{storeId}")
    public Store AssignLocationtoStore(@PathVariable("locationId") Long locationId, @PathVariable("storeId") Long storeId) {
        return storeService.AssignLocationtoStore(locationId, storeId);

    }


    @PostMapping("/addStoreWithQRCode")
    public Store addStoreWithQRCode(@RequestBody Store store) throws IOException, WriterException {


        QRCodeGenerator.generateQRCode(store);
        return storeService.add(store);
    }


    @GetMapping("/AnalyzePostComments")
    public Map<String, Map<String,Float>> analizeSentimentOfComments() {
        return storeService.analizeSentimentOfComments();

    }

    @GetMapping("/setLatLng/{storeId}")
    public ResponseEntity<Map<String, Object>> setLatLngToStore(@PathVariable("storeId") Long storeId) {
        return storeService.setLatLngToStore(storeId);

    }
}

