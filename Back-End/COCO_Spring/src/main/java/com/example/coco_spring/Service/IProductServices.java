package com.example.coco_spring.Service;


import com.example.coco_spring.entity.Product;

import java.util.List;

public interface IProductServices {
    List<Product> retrieveAllProducts();

    Product addAndUpdateProduct(Product product);


    Product retrieveProduct (Long productId);

    void deleteProduct(Long productId);
}
