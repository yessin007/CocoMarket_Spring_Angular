package com.example.coco_spring.Controller.Cart;


import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.*;
import com.example.coco_spring.Repository.*;

import com.example.coco_spring.Service.Cart.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.*;
import com.example.coco_spring.Repository.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/cart/")
public class CartController {

    CartService cartService;
    UserRepository userRepository;
    CartRepsitory cartRepsitory;
    ProductRepository productRepository;

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
        return cartService.add(cart);
    }

    @PutMapping("/update_cart")
    public Cart updateCart(@RequestBody Cart cart){
        return cartService.update(cart);
    }

    @DeleteMapping("/delete_cart/{cartId}")
    public void deleteCart(@PathVariable("cartId") Long cartId){
        cartService.delete(cartId);
    }



    ///////////////////////////////////////////////////////////////////////////////////////


    @DeleteMapping("/delete_cartItem/{cartItemId}")
    public void deleteCartItem(@PathVariable("cartItemId") Long cartItemId){
        cartService.deleteCARTITEM(cartItemId);
    }


//    @PostMapping("/addProductToCart/{cartId}/{productid}")
//    public List<Product> addProductToCart(@PathVariable("cartId") Long cartId,@PathVariable("productid") Long productId){
//        return  cartService.addProductToCart(cartId, productId);
//    }

//    @PostMapping("/users")
//    public List<Product> addProductToCartByUser(@RequestParam("userId") Long userId,
//                                                 @RequestParam("productId") Long productId,
//                                                    @RequestParam("quantity") Long quantity) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("User not found with id " + userId));
//        Cart cart = user.getCart();
//
//        if (cart == null) {
//            cart = new Cart();
//            user.setCart(cart);
//        }
//        Product product = productRepository.findById(productId).orElse(null);
//        product.setCart(cart);
//        cart.getProducts().add(product);
//        cart.setProductQuantity(quantity);
//        cartRepsitory.save(cart);
//
//        return cart.getProducts();
//    }


//    @PostMapping("/QuantityProductToCart/{productId}/{quantity}")
//    public Long AssignQunatityToProductInCart(@PathVariable("productId") Long productId,
//                                           @PathVariable("quantity") int quantity,
//                                           Principal principal) {
//        String username = principal.getName();
//        //User user = userRepository.findByUsername(username).get();
//        Product product = productRepository.findById(productId).orElseThrow();
//        Cart cart = username.getCart();
//        cartService.addItem(product, quantity);
//        cartRepsitory.save(cart);
//        return user.getId();
//    }

//    @GetMapping("/addToCart/{productId}")
//    public Cart addToCart(@PathVariable("productId") Long productId){
//        return cartService.addToCart(productId);
//    }

    @GetMapping("/getCartDetails")
    public List<Cart> getCartDetails(){
        return cartService.getCartDetails();
    }

    @PostMapping("/cartItem/{cartId}/{productId}/{quantity}")
    public void addProductToCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId, @PathVariable("quantity") Long quantity) {
        cartService.addProductToCart(cartId,productId,quantity);
    }

    @GetMapping("/getallCartItem")
    public List<CartItem> retrieveCartItemList(){
        return cartService.retrieveCartItemList();
    }


    @GetMapping("/getCartItemsWithProducts/{cartId}")
    public List<CartItem> getCartItemsWithProducts(@PathVariable Long cartId) {
        return cartService.getCartItemsWithProducts(cartId);
    }


    @GetMapping("/getindexCart")
    public int getindexCart(){
        return cartService.getindexCart();
    }




}
