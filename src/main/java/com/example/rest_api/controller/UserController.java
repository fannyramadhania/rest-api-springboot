package com.example.rest_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest_api.entity.Response;
import com.example.rest_api.entity.User;
import com.example.rest_api.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User entity) {

        System.out.println(
                "Received user: "
                        + entity.getName()
                        + ", Age: "
                        + entity.getAge()
                        + ", Email: "
                        + entity.getEmail());

        try {
            User user = userService.creatUser(entity);
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping ("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "Success";
    }


    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User entity) {
      return   userService.updateUser(id, entity);
     
    }
}