package com.example.rest_api.service;

import com.example.rest_api.entity.User;
import com.example.rest_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User creatUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }
    
    public User updateUser(Long id,User entity) {
        User existingUser = userRepository.findById(id).orElse(null);
        existingUser.setName(entity.getName());
        existingUser.setEmail(entity.getEmail());
        existingUser.setAge(entity.getAge());
      return  userRepository.save(existingUser);
       

    }
}