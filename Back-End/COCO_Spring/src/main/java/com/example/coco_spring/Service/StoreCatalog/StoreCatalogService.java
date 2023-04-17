package com.example.coco_spring.Service.StoreCatalog;


import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

import java.time.LocalDate;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StoreCatalogService implements ICRUDService<StoreCatalog,Long>,IStoreCatalogService {

    StoreCatalogRepository storeCatalogRepository;
    StoreRepository storeRepository;
    ProductRepository productRepository ;

    StoreCatalogLikeRepository storeCatalogLikeRepository;
    UserRepository userRepository;

    UserDataLoadRepo userDataLoadRepo;
    CategoryAdverRepo categoryAdverRepo;

    ProductRepository productRepository;



    @Override
    public List<StoreCatalog> findAll() {
        return storeCatalogRepository.findAll();

    }

    @Override
    public StoreCatalog retrieveItem(Long idItem) {
        return storeCatalogRepository.findById(idItem).get();
    }


    @Override
    public StoreCatalog add(StoreCatalog class1) {
        return storeCatalogRepository.save(class1);
    }

    public StoreCatalog add1( String catalogName, String catalogDescription, Date date) {
        // Perform input validation here
        if ( catalogName == null || catalogDescription == null || date == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        // Create a new StoreCatalog object with the provided attributes
        StoreCatalog newCatalog = new StoreCatalog();
        newCatalog.setCatalogName(catalogName);
        newCatalog.setCatalogDescription(catalogDescription);
        newCatalog.setDate(date);

        // Save the new StoreCatalog object using the storeCatalogRepository
        return storeCatalogRepository.save(newCatalog);
    }




    @Override
    public void delete(Long aLong) {
        storeCatalogRepository.deleteById(aLong);
    }

    @Override
    public StoreCatalog update(StoreCatalog Classe11) {
        return storeCatalogRepository.save(Classe11);
    }

    @Override
    public void affecterStoreCatalogAStore(Long catalogId, Long storeId) {
        StoreCatalog storeCatalog =storeCatalogRepository.findById(catalogId).get();
        Store store=storeRepository.findById(storeId).get();
        store.setStoreCatalog(storeCatalog);
        storeRepository.save(store);
    }

    @Override
    public StoreCatalog findStoreCatalogByName(String catalogName) {

        return storeCatalogRepository.findStoreCatalogByCatalogName(catalogName);
    }

    @Override
    public StoreCatalog findStoreCatalogByDescription(String description) {
        return storeCatalogRepository.findStoreCatalogByCatalogDescription(description);
    }




    public void DetctaDataLoad (String ch , Long idUser) {
        List<UserDataLoad> ul = userDataLoadRepo.findAll();
        User u = userRepository.findById(idUser).orElse(null);
        for (CategoryAdve string : categoryAdverRepo.findAll()) {
            if (ch.contains(string.getNameCategory())) {
                if (existDataForUser(string.getNameCategory(),idUser) == true) {
                    UserDataLoad l = getData(string.getNameCategory(),idUser);
                    l.setNbrsRequet(l.getNbrsRequet()+1);
                    userDataLoadRepo.save(l);
                }
                else {
                    UserDataLoad l1 = new UserDataLoad();
                    l1.setCategorieData(string.getNameCategory());
                    l1.setUser(u);
                    l1.setNbrsRequet(1);
                    userDataLoadRepo.save(l1);

                }
            }
        }
    }


    public Boolean existDataForUser(String ch,Long IdUser) {
        Boolean x = false;
        for (UserDataLoad userDataLoad : userDataLoadRepo.findAll()) {
            if (userDataLoad.getCategorieData().equals(ch) && userDataLoad.getUser().getId() == IdUser) {
                x = true;
            }
        } return x;
    }
    public UserDataLoad getData(String ch,Long IdUser) {
        UserDataLoad x = null;
        for (UserDataLoad userDataLoad : userDataLoadRepo.findAll()) {
            if (userDataLoad.getCategorieData().equals(ch) && userDataLoad.getUser().getId() == IdUser) {
                x = userDataLoad;
            }
        } return x;
    }











    public StoreCatalogLike addLike_to_Post(StoreCatalogLike storeCatalogLike, Long idStoreCatalog, Long idUser) {
        int x=0;
        boolean y =false;
        StoreCatalog storeCatalog = storeCatalogRepository.findById(idStoreCatalog).orElse(null);
        User u = userRepository.findById(idUser).orElse(null);
        for (StoreCatalogLike l : storeCatalogLikeRepository.findAll()){
            if(l.getStoreCatalog().getCatalogId() == idStoreCatalog && l.getUser().getId()== idUser){
                x=1;
                y=l.getIsLiked();
                storeCatalogLikeRepository.delete(l);
            }
        }
        if (x ==0 || (x == 1 && y!=storeCatalogLike.getIsLiked()	)){
            DetctaDataLoad(storeCatalog.getCatalogDescription(),idUser);
            storeCatalogLike.setUser(u);
            storeCatalogLike.setStoreCatalog(storeCatalog);
            storeCatalogLike.setLikedAt(LocalDate.now());
            storeCatalogLikeRepository.save(storeCatalogLike);
        }
            return storeCatalogLike;
    }

    public void affectFavToUser(Long userId,Long catalogId){
        User user =userRepository.findById(userId).orElse(null);
        StoreCatalog storeCatalog=storeCatalogRepository.findById(catalogId).orElse(null);
        user.getFavories().add(storeCatalog);
        userRepository.save(user);

    }


    public String observeProductCategory(Long catalogId,Long productId){
        StoreCatalog storeCatalog = storeCatalogRepository.findById(catalogId).orElse(null);
        List<Product> products = storeCatalog.getStore().getProducts();
        Product product =productRepository.findById(productId).orElse(null);
        for (Product p : products){
            if (p.equals(product));
            return String.valueOf(product.getProductCategory());
        }

        return "not found";
    }





}
