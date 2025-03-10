package com.example.mylife;

import org.hibernate.cfg.Environment;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class MyLifeApplication {
//    private static final Logger LOG = LoggerFactory.getLogger(MyLifeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MyLifeApplication.class, args);

//        SpringApplication app= new SpringApplication(MyLifeApplication.class);
//        Environment environment= app.run(args).getEnvironment();
//        LOG.info("启动成功啦，欢迎呀（- -）");
//        LOG.info(LOG.info("地址：\thttp://127.0.0.1:{}",environment.get("server.port"));
    }
}
