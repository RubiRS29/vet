package com.vet.web.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class Login {

    @RequestMapping("/")
    public String helloGFG() {
        return "Hello GeeksForGeeks";
    }




}
