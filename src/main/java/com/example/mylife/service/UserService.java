package com.example.mylife.service;

import com.example.mylife.model.User;
import com.example.mylife.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private List<User> users = new ArrayList<>();


    public void registerUser(User user) {
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isUserExists(String username){
        return userRepository.existsByUsername(username);
    }


    public boolean isUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User validateAdmin(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password) && user.hasRole("ADMIN")) {
            return user;
        }
        return null;
    }
}
