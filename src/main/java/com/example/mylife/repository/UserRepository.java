package com.example.mylife.repository;

import com.example.mylife.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void deleteByUsername(String username);
    boolean existsByUsername(String username); //注册时候判断用户是否已经存在
    boolean existsByEmail(String email); //注册时候判断邮箱是否已经存在
}
