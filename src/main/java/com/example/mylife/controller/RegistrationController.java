package com.example.mylife.controller;

import com.example.mylife.model.User;
import com.example.mylife.repository.UserRepository;
import com.example.mylife.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam("username") String username,
                                   @RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   RedirectAttributes redirectAttributes) {
        // 记录接收到的表单数据
        logger.info("Received username: " + username);
        logger.info("Received email: " + email);
        logger.info("Received password: " + password);

        //查询用户名是否存在
        if (userService.isUserExists(username)){
            logger.info("用户名已存在："+username);
            redirectAttributes.addFlashAttribute("errorMessage","用户名已存在");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("用户名已存在");
        }

        //查询邮箱是否存在
        if (userService.isUserExistsByEmail(email)){
            logger.info("邮箱已存在："+email);
            redirectAttributes.addFlashAttribute("errorMessage","邮箱已存在");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("邮箱已存在");
        }


        // 创建User对象并设置属性
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        try {
            // 将用户保存到数据库
            userService.registerUser(user);
            // 返回成功消息
            redirectAttributes.addFlashAttribute("successMessage","注册成功");
            return ResponseEntity.ok("注册成功");
        } catch (Exception e) {
            // 记录异常信息
            logger.error("Error occurred during user registration", e);
            // 在重定向中添加失败标记
            redirectAttributes.addFlashAttribute("registrationFailed", true);
            // 注册失败后重定向到注册页面
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("注册失败");
        }
    }

}


