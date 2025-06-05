package com.sachi.login_g.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Oauthcontroller {

    @RequestMapping("/")
    public String Home() {
        return "Hello, this is the OAuth controller!";
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
