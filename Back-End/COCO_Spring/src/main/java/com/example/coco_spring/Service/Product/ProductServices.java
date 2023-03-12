package com.example.coco_spring.Service.Product;


import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.Product.IProductServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServices implements IProductServices {
    ProductRepository productRepository;

    @Override
    public List<Product> retrieveAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product addAndUpdateProduct(MultipartFile image,Product product) throws IOException {
        product.setImages(image.getBytes());
        return productRepository.save(product);
    }
    @Override
    public Product retrieveProduct(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }


    public List<Product> findByName(String name) {
        return productRepository.findByProductName(name);
    }

    public ProductCategory TopProductCategoryByUserThisWeek (User u){ // add by Ahmed lasmar for the daily offers mail
        Long userId =   u.getId();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date startOfWeek = calendar.getTime();
        calendar.add(Calendar.DATE, 6);
        Date endOfWeek = calendar.getTime();
        return productRepository.findTopProductCategoryByUserThisWeek(userId, startOfWeek, endOfWeek);
    }

    public List<Product> findTop4ProductsByCategoryOrderByRecentlyAdded(ProductCategory categorie) {

        List<Product> top4ProductsByCategory = new ArrayList<>();

            Pageable pageable = PageRequest.of(0, 4);
            List<Product> top4Products = productRepository.findTop4ProductsByCategoryOrderByRecentlyAdded(categorie, pageable);
            top4ProductsByCategory.addAll(top4Products);


        return top4ProductsByCategory;
    }
}
