package com.vet.web.app.controller;


import com.vet.web.app.entity.dto.UserDto;
import com.vet.web.app.response.Response;
import com.vet.web.app.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class Login {

    private final LoginService loginService;

    public Login(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/")
    public String helloGFG() {
        return "Hello GeeksForGeeks";
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Response> register(@RequestBody UserDto user) {

        return loginService.register(user);

    }


}
