package com.example.mylife.repository;

import com.example.mylife.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Images, Long> {

}
