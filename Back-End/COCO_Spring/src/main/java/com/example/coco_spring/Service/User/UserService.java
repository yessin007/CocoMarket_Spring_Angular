package com.example.coco_spring.Service.User;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private EmailService emailService;
    private final PasswordEncoder passwordEncoder;

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
<<<<<<< HEAD
=======

    public void updateAuthenticationType(String username, String oauth2ClientName) {
        AuthenticationProvider authType = AuthenticationProvider.valueOf(oauth2ClientName.toUpperCase());
        userRepository.updateAuthenticationType(username, authType);
    }
>>>>>>> parent of 8919370 (errrrr)*/

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
            user.setPassword(passwordEncoder.encode(pwd));
            return "done";
        }
    }
}
