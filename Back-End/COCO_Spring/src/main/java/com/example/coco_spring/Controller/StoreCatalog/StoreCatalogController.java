package com.example.coco_spring.Controller.StoreCatalog;

import com.example.coco_spring.Entity.*;

import com.example.coco_spring.Repository.NotificationRepository;
import com.example.coco_spring.Repository.StoreCatalogRepository;

import com.example.coco_spring.Repository.UserRepository;
import com.example.coco_spring.Service.EmailService;

import com.example.coco_spring.Service.StoreCatalog.StoreCatalogService;
import com.example.coco_spring.Service.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import javax.mail.MessagingException;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/storecatalog")
public class StoreCatalogController {

    StoreCatalogService storeCatalogService;

NotificationRepository notificationRepository ;
StoreCatalogRepository storeCatalogRepository ;

    @Autowired
    EmailService emailService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;




    @PostMapping("/addStoreCatalog")
    public StoreCatalog add(@RequestBody StoreCatalog storeCatalog,
                            @RequestPart("imageFile") MultipartFile[] file){

        try {
            Set<ImageAM> images = uploadImage(file);
            storeCatalog.setCatalogImages(images);
            return storeCatalogService.add(storeCatalog);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null ;
        }
    }

    public Set<ImageAM> uploadImage(MultipartFile[] multipartFiles)  throws IOException {
        Set<ImageAM> ImageAMs = new HashSet<>();
        for(MultipartFile file : multipartFiles){
            ImageAM ImageAM =new ImageAM(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            ImageAMs.add(ImageAM);
        }
        return ImageAMs ;
    }

    @GetMapping("/get_all_StoreCatalog")
    public List<StoreCatalog> findAll(){
        return storeCatalogService.findAll();
    }

    @PutMapping("/updateStoreCatalog")
    public StoreCatalog update(@RequestBody StoreCatalog Classe1){
        return storeCatalogService.update(Classe1);
    }
    @DeleteMapping("/deleteStoreCatalog/{catalogId}")
    public void delete(@PathVariable("catalogId") Long aLong){
        storeCatalogService.delete(aLong);
    }

    @GetMapping("getById_StoreCatalog/{catalogId}")
    public StoreCatalog retrieveItem(@PathVariable("catalogId") Long idItem){
        return storeCatalogService.retrieveItem(idItem);
    }

    @PutMapping("/affecterStoreCatalogAStore/{catalogId}/{storeId}")
    public void affecterStoreCatalogAStore(@PathVariable("catalogId") Long catalogId,@PathVariable("storeId") Long storeId){
        storeCatalogService.affecterStoreCatalogAStore(catalogId,storeId);
    }

    @GetMapping("/findStoreCatalogByName/{catalogName}")
    public StoreCatalog findStoreCatalogByName(@PathVariable("catalogName") String catalogName){

        return storeCatalogService.findStoreCatalogByName(catalogName);
    }

    @GetMapping("findStoreCatalogByDescription/{description}")
    public StoreCatalog findStoreCatalogByDescription(@PathVariable("description") String description){
        return storeCatalogService.findStoreCatalogByDescription(description);
    }




    @PostMapping("add1")
    public ResponseEntity<StoreCatalog> add1( @RequestParam String catalogName, @RequestParam String catalogDescription,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        StoreCatalog storeCatalog = storeCatalogService.add1(catalogName, catalogDescription, date);
        return new ResponseEntity<>(storeCatalog, HttpStatus.CREATED);
    }
    @GetMapping("observeProductCategory/{catalogId}/{productId}")
    public String observeProductCategory(@PathVariable("catalogId") Long catalogId,@PathVariable("productId") Long productId){
        return storeCatalogService.observeProductCategory(catalogId,productId);


    }

    @PostMapping("/add-Like-post/{IdStoreCatalog}/{IdUser}")
    @ResponseBody
    public StoreCatalogLike addLike_to_Post(@RequestBody(required = false) StoreCatalogLike postLike, @PathVariable("IdStoreCatalog") Long IdStoreCatalog, @PathVariable("IdUser") User u) {
        StoreCatalogLike pos1 = new StoreCatalogLike();
        pos1.setIsLiked(true);

        return storeCatalogService.addLike_to_Post(pos1,IdStoreCatalog,u.getId());
    }
    @PostMapping("/add-DisLike-post/{IdStoreCatalog}/{IdUser}")
    @ResponseBody
    public StoreCatalogLike addDisLike_to_Post(@RequestBody(required = false) StoreCatalogLike postLike, @PathVariable("IdStoreCatalog") Long IdStoreCatalog, @PathVariable("IdUser") User u) {
        StoreCatalogLike pos1 = new StoreCatalogLike();
        pos1.setIsLiked(false);

        return storeCatalogService.addLike_to_Post(pos1,IdStoreCatalog,u.getId());
    }

    @PostMapping("affectFavToUser/{userId}/{catalogId}")
    public void affectFavToUser(@PathVariable("userId") Long userId,@PathVariable("catalogId") Long catalogId){
        storeCatalogService.affectFavToUser(userId,catalogId);
    }
/*
    @GetMapping("observeProductCategory/{catalogId}/{productId}")
    public String observeProductCategory(@PathVariable("catalogId") Long catalogId,@PathVariable("productId") Long productId){
        return storeCatalogService.observeProductCategory(catalogId,productId);

    }

*/
    @PostMapping("sendEmailToStoreCatalog/{userId}/{productId}/{catalogId}/{subject}/{message}")
    public void sendEmailToStoreCatalog(@PathVariable("userId") Long userId,@PathVariable("productId")Long productId,@PathVariable("catalogId")Long catalogId,@PathVariable("subject") String subject,@PathVariable("message") String message) throws MessagingException {
        User user = userRepository.findById(userId).get();
        List<String> interests = userService.findtheinterestsofbuyers(user.getId());
        emailService.sendEmailToStoreCatalog(user,interests,productId,catalogId,subject,message);
    }

    @GetMapping("findStoreId/{catalogId}")
    public Store findStoreId(@PathVariable("catalogId") Long catalogId){
        return storeCatalogService.findStoreId(catalogId);
    }





    }
