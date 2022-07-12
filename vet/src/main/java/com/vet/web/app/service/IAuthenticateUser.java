package com.vet.web.app.service;

import com.vet.web.app.entity.dto.UserAuth;
import com.vet.web.app.entity.dto.UserDto;
import com.vet.web.app.response.Response;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IAuthenticateUser {

    ResponseEntity<Response> signup(UserDto user);

    void refreshToken(HttpServletRequest request,
                      HttpServletResponse response) throws IOException;
}
