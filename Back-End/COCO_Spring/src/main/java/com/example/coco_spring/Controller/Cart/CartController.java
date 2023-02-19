package com.example.coco_spring.Controller.Cart;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import com.example.coco_spring.Service.Cart.CartService;
import com.example.coco_spring.Service.Cart.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    CartService cartService;
    ICartService iCartService;

    @GetMapping("/retrive_all_carts")
    public List<Cart> retrieveCartList(){
        return cartService.findAll();
    }

    @GetMapping("/retrive_cart/{cartId}")
    public Cart retrieveCart(@PathVariable("cartId") Long cartId){
        return cartService.retrieveItem(cartId);
    }

    @PostMapping("/add_cart")
    public Cart addCart(@RequestBody Cart cart){
        return cartService.addccart(cart);
    }

    @PutMapping("/update_cart")
    public Cart updateCart(@RequestBody Cart cart){
        return cartService.update(cart);
    }

    @DeleteMapping("/delete_cart/{cartId}")
    public void deleteCart(@PathVariable("cartId") Long cartId){
        cartService.delete(cartId);
    }



}
