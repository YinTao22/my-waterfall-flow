package com.example.mylife.repository;


import com.example.mylife.model.AdminInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminInfo, String> {
}


