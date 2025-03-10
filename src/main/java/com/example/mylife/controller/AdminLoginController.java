package com.example.mylife.controller;


import com.example.mylife.model.AdminInfo;
import com.example.mylife.model.User;
import com.example.mylife.service.AdminService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth") // 添加一个类级别的请求映射
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    @CrossOrigin(origins = "*")
    @PostMapping("/adminlogin")
    public void login(@RequestParam String adminname,
                      @RequestParam String password, HttpServletResponse response, HttpSession session)
        throws IOException {
        AdminInfo adminInfo = adminService.validateAdmin(adminname, password);
        if (adminInfo != null) {
            // Store user information in session
            User user = new User(adminname, Set.of("ADMIN"));
            session.setAttribute("user", user);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid adminname or password");
        }
    }

}





