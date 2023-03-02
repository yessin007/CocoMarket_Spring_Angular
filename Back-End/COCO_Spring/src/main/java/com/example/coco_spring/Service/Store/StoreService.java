package com.example.coco_spring.Service.Store;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class StoreService implements ICRUDService<Store,Long> , IMPCocoService {
    StoreRepository storeRepository;
    ProductRepository productRepository;
    OrderRepository orderRepository;
    CartRepsitory cartRepsitory;
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

    @Override
    public Map<String, Integer> getSalesStatisticsForStoreByMonth(Long storeId) {
       /* Map<String, Integer> salesStatistics = new HashMap<>();

        // Récupérer toutes les commandes passées par le magasin
        List<Order> orders = orderRepository.findAllByStoreId(storeId);

        // Boucler sur toutes les commandes et calculer les statistiques de vente pour chaque mois
        for (Order order : orders) {
            List<Product> products = order.getProducts();
            for (Product product : products) {
                // Vérifier si le produit a été vendu ce mois-ci
                LocalDate orderDate = order.getOrderDate().toLocalDate();
                String month = orderDate.getMonth().toString();
                int year = orderDate.getYear();
                String monthYear = month + " " + year;
                int quantitySold = product.getQuantitySold();

                // Ajouter la quantité vendue au mois correspondant dans la map des statistiques de vente
                if (salesStatistics.containsKey(monthYear)) {
                    int currentQuantity = salesStatistics.get(monthYear);
                    salesStatistics.put(monthYear, currentQuantity + quantitySold);
                } else {
                    salesStatistics.put(monthYear, quantitySold);
                }
            }
        }*/

        return null;
    }

    @Override
    public List<Order> findAllByStoreId(Long storeId) {
        List<Order> orders = new ArrayList<>();

        // Récupérer le store associé à l'ID spécifié
        Store store = storeRepository.findById(storeId).orElse(null);

        if (store != null) {
            // Récupérer tous les produits associés à ce store
            List<Product> products = store.getProducts();

            // Pour chaque produit, récupérer tous les paniers associés
            for (Product product : products) {
                List<Cart> carts = product.getCarts();

                // Pour chaque panier, récupérer la commande associée
                for (Cart cart : carts) {
                    Order order = cart.getOrder();

                    // Si la commande existe, l'ajouter à la liste des commandes
                    if (order != null) {
                        orders.add(order);
                    }
                }
            }
        }

        return orders;
    }

    @Override
    public List<Product> findProductsByStoreId(Long storeId) {
        List<Cart> carts = cartRepsitory.findAllByStoreId(storeId);
        List<Product> products = productRepository.findAllByCartIn(carts);
        return products;
    }

    @Override
    public Map<Product, Integer> getMonthlySalesByProductAndStore(Store store, int year, int month) {
        List<Product> products = store.getProducts();
        Map<Product, Integer> monthlySales = new HashMap<>();

        for (Product product : products) {
            int totalQuantitySold = 0;

            for (Cart cart : product.getCarts()) {
                if (cart.getOrder() != null && cart.getOrder().getOrderDate().getYear() == year &&
                        cart.getOrder().getOrderDate().getTime()== month) {
                    totalQuantitySold += cart.getProductQuantity();
                }
            }

            monthlySales.put(product, totalQuantitySold);
        }

        return monthlySales;
    }



}
