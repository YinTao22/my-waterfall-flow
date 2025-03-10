package com.example.mylife.service;

import com.example.mylife.model.User;
import com.example.mylife.repository.UserRepository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl extends UserService {
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private SessionFactory sessionFactory;

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}