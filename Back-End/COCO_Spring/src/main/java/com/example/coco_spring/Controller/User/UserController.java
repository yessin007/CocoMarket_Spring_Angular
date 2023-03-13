package com.example.coco_spring.Controller.User;

import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.User.UserService;
import com.example.coco_spring.Service.User.UserToExel;
import com.example.coco_spring.Service.User.UserToPDF;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@Lazy
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
    @GetMapping(value = "/Rapport/excel")
    public ModelAndView createExcel(HttpServletRequest request, HttpServletResponse response) {
        //create data
        List<User> list = userService.findAll();

        return new ModelAndView(new UserToExel(), "usersList", list);
    }

    @GetMapping(value = "/Rapport/pdf")
    public ModelAndView createPdf(HttpServletRequest request, HttpServletResponse response) {
        String typeReport = request.getParameter("type");

        //create data
        List<User> list = userService.findAll();

        return new ModelAndView(new UserToPDF(), "usersList", list);
    }
}
