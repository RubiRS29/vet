package com.vet.web.app.controller;


import com.vet.web.app.entity.dto.UserDto;
import com.vet.web.app.response.Response;
import com.vet.web.app.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/Hello_world")
    public String helloGFG() {
        return "Hello world";
    }

    @PostMapping(value = "/signing")
    public void authenticateUser(@RequestBody String user) {
        loginService.loadUserByUsername(user);
    }


    @PostMapping(value = "/signup")
    public ResponseEntity<Response> register(@RequestBody UserDto user) {

        return loginService.signup(user);

    }

    @GetMapping(value = "/token/refresh")
    public void register(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        loginService.refreshToken(request, response);

    }
}
