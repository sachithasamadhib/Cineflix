package com.sachi.login_g;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class LoginGApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginGApplication.class, args);
    }

}
