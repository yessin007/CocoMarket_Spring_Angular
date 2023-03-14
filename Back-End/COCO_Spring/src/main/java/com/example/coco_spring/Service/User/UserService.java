package com.example.coco_spring.Service.User;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

@Service
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public List<String> findtheinterestsofbuyers(Long userId){
        User user = userRepository.findById(userId).get();
        List<Product> products =user.getCart().getProducts();
        Map<String,Integer> categoryCountMap = new HashMap<>();




        for (Product product : products){
            String category = String.valueOf(product.getProductCategory());
            int count = categoryCountMap.getOrDefault(category,0);
            categoryCountMap.put(category,count+1);
        }
        List<String> topCategories = categoryCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return topCategories;
    }

    public List<StoreCatalog> getAllUserFavories(Long userId){
        User user =userRepository.findById(userId).orElse(null);
        return user.getFavories();
    }
}
