package com.cts.springboot.banking.controller.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello!!";
    }
}
