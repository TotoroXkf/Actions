package com.example.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run();


    }

    @RequestMapping("/")
    String home() {
        return "Hello World";
    }

    @RequestMapping("/getData")
    User getJson() {
        User user = new User();
        user.setAge(12);
        user.setId("11324325235");
        user.setName("xkf");
        return user;
    }
}
