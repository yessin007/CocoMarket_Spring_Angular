package com.example.coco_spring.Service.User;

import com.example.coco_spring.Auth.AuthenticationService;
import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.Delivery.LocationService;
import com.example.coco_spring.Service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

@Service
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private EmailService emailService;

    private AuthenticationService authenticationService;

    LocationService locationService;
    ClientLocationRepository clientLocationRepository;
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

    public User updateRoleUser(Long id, String r) {
        User user = userRepository.findById(id).get();
        user.setRoles(Role.valueOf(r));
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }/*

    public void updateAuthenticationType(String username, String oauth2ClientName) {
        AuthenticationProvider authType = AuthenticationProvider.valueOf(oauth2ClientName.toUpperCase());
        userRepository.updateAuthenticationType(username, authType);
    }*/

    public User setUserExpiration (Long id,Integer duration) throws MessagingException {
        User user = userRepository.findById(id).get();
        user.setExpired(true);
        LocalDate currentDate = LocalDate.now();
        LocalDate unexpirDate = currentDate.plusDays(duration);
        Date dateToUnexpire = Date.from(unexpirDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        user.setDateToUnexired(dateToUnexpire);
        userRepository.save(user);
        emailService.sendBlockEmail(user,duration);
        return user;
    }

    public User setUserunExpiration(Long id) {
        User user = userRepository.findById(id).get();
        user.setExpired(false);
        userRepository.save(user);
        return user;
    }


    public User demReserPassword(String email) throws MessagingException {
        User user = userRepository.findByEmail(email).get();
        Random random = new Random();
        int randomNumber = random.nextInt(90000000) + 10000000;
        user.setCodeReset(randomNumber);
        emailService.sendCodeReset(user);
        return user;
    }

    public String reserPassword(Integer code, String pwd) {
        User user = userRepository.findByCodeReset(code).get();
        if (user == null){
            return "User Not Found";
        }
        else {
            user.setPassword(authenticationService.criptMDP(pwd));
            return "done";
        }
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

    public ResponseEntity<Map<String, Object>> setLatLngToUser(Long userId) {
        try {
            String geolocationResponse = locationService.getGeolocation();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(geolocationResponse);
            double latitude = root.get("location").get("lat").asDouble();
            double Langitude = root.get("location").get("lng").asDouble();
            ClientLocation clientLocation = new ClientLocation();
            clientLocation.setLatitude(latitude);
            clientLocation.setLongitude(Langitude);
            clientLocationRepository.save(clientLocation);
            User user = userRepository.findById(userId).get();
            AssignLocationtoUser(clientLocation.getId(),user.getId());

            Map<String, Object> response = new HashMap<>();
            response.put("latitude", latitude);
            response.put("langitude", Langitude);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void AssignLocationtoUser(Long locationId, Long  userId) {
        User user = userRepository.findById(userId).get();
        ClientLocation clientLocation = clientLocationRepository.findById(locationId).get();
        user.setClientLocation(clientLocation);
        userRepository.save(user);
    }


}
