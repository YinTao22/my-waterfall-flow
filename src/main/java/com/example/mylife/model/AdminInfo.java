package com.example.mylife.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "admin")
public class AdminInfo {

    @Id
    private String adminname;
    private String password;

    // Constructors
    public AdminInfo() {
    }

    public AdminInfo(String adminname, String password) {
        this.adminname = adminname;
        this.password = password;
    }

    // Getters and setters
    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


