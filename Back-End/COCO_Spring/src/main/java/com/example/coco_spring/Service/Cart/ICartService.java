package com.example.coco_spring.Service.Cart;


import com.example.coco_spring.Entity.*;

import java.util.List;

public interface ICartService {

    List<Product> addProductToCart(Long cartId, Long productId);

    Cart AssignCartToUser(Long cartid, Long userId);


}
