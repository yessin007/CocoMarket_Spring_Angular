package com.example.coco_spring.Service.Cart;


import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.*;
import com.example.coco_spring.Repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class CartService implements ICRUDService<Cart,Long> {

    CartRepsitory cartRepsitory;

    CartItemRepository cartItemRepository;

    ProductRepository productRepository;

    UserRepository userRepository;


    public int getindexCart(){
       return cartItemRepository.findAll().size();
    }

    @Override
    public List<Cart> findAll() {

        return cartRepsitory.findAll();
    }

    public List<CartItem> retrieveCartItemList(){
        return cartItemRepository.findAll();
    }

    @Override
    public Cart retrieveItem(Long idCart) {
        return cartRepsitory.findById(idCart).get();
    }

    @Override
    public Cart add(Cart cart) {

        return cartRepsitory.save(cart);
    }

    @Override
    public void delete(Long cartId) {

        cartRepsitory.deleteById(cartId);
    }

    @Override
    public Cart update(Cart cart) {
        return cartRepsitory.save(cart);
    }



    /////////////////////////////////////////////////////////////////

    public void deleteCARTITEM(Long cartItemId){
        cartItemRepository.deleteById(cartItemId);
    }


//    @Override
//    public List<Product> AddProductToCart(Long idcart, Long idproduct) {
//        Cart cart = cartRepsitory.findById(idcart).orElse(null);
//        Product product = productRepository.findById(idproduct).orElse(null);
//
//
//
//        cart.setProducts(product);
//
//
//        return null;
//    }

//    @Override
//    public List<Product> addProductToCart(Long cartId, Long productId) {
//        Cart cart = cartRepsitory.findById(cartId)
//                .orElseThrow(() -> new NotFoundException("Cart not found with id " + cartId));
//
//        Product product = productRepository.findById(productId).orElse(null);
//
//        cart.getProducts().add(product);
//        //product.setCart(cart);
//        cartRepsitory.save(cart);
//
//        return cart.getProducts();
//    }



//    @Override
//    public Cart AssignCartToUser(Long cartid, Long userId) {
//        Cart cart = cartRepsitory.findById(cartid).orElse(null);
//        User user = userRepository.findById(userId).orElse(null);
//
//
//        user.getCart().setUser(user);
//        userRepository.save(user);
//
//        return cart;
//    }



        public Map<Product, Integer> items = new HashMap<>();

        public Map<Product, Integer> addItem(Product product, int quantity) {
            if (items.containsKey(product)) {
                int currentQuantity = items.get(product);
                items.put(product, currentQuantity + quantity);
            } else {
                items.put(product, quantity);
            }
            return items;
        }

        public void removeItem(Product product) {
            items.remove(product);
        }

        public Map<Product, Integer> getItems() {
            return items;
        }

        public int getTotalQuantity() {
            int totalQuantity = 0;
            for (int quantity : items.values()) {
                totalQuantity += quantity;
            }
            return totalQuantity;
        }

        public double getTotalPrice() {
            double totalPrice = 0;
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                totalPrice += product.getPrice() * quantity;
            }
            return totalPrice;
        }

//        public Cart addToCart(Long productId){
//            Product product =productRepository.findById(productId).orElse(null);
//            //User user = userRepository.findById(userId).orElse(null);
//
//
//
//            if(product!=null ){
//                Cart cart = new Cart(product);
//                cartRepsitory.save(cart);
//            }
//        return  null;
//        }

        public List<Cart> getCartDetails(){
            return  cartRepsitory.findAll();
        }



    public void addProductToCart(Long cartId, Long productId, Long quantity) {
        Cart cart = cartRepsitory.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id " + cartId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    public Product getProductById(Long productId) {
        Product product = productRepository.findById(productId).get();
        return mapProductToProductDTO(product);
    }

    private Product mapProductToProductDTO(Product product) {
        Product productDTO = new Product();
        productDTO.setProductId(product.getProductId());
        productDTO.setQuantity(product.getQuantity());

        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    public List<CartItem> getCartItemsWithProducts(Long cartId) {
        Cart cart = cartRepsitory.findById(cartId).get();
        List<CartItem> cartItems = cart.getItems();
        List<CartItem> cartItemDTOs = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            Product productDTO = getProductById(cartItem.getProduct().getProductId());
            CartItem cartItemDTO = mapCartItemToCartItemDTO(cartItem);
            cartItemDTO.setProduct(productDTO);
            cartItemDTOs.add(cartItemDTO);
        }
        return cartItemDTOs;
    }

    private CartItem mapCartItemToCartItemDTO(CartItem cartItem) {
        CartItem cartItemDTO = new CartItem();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        return cartItemDTO;
    }



}


