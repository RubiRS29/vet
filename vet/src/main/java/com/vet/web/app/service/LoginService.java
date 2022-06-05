package com.vet.web.app.service;


import com.vet.web.app.entity.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


/*
    1.- First to all, make the register form - just

 */

@Service
public class LoginService {

    /*
        We have to make sure what kind of user is,
        e.i. veterinarian, adopter or refuge
     */
    public ResponseEntity<String> register(UserDto user) {



        return null;
    }


}
