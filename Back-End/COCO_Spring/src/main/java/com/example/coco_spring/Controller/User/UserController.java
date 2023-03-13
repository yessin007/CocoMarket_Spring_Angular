package com.example.coco_spring.Controller.User;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/updateRole/{id}/{role}")
    public String updateRoleUser(@PathVariable("id") Long id,@PathVariable("role") String r) {
        User u =userService.updateRoleUser(id,r);
        return "User "+u.getName()+" is "+u.getRoles();
    }

    @PutMapping("/block/{id}/{duration}")
    public String blockUser(@PathVariable("id") Long id,@PathVariable("duration")Integer dur) {
        User u= userService.setUserExpiration(id, dur);
        return "User "+u.getName()+" is blocker for "+dur+"days";
    }
}
