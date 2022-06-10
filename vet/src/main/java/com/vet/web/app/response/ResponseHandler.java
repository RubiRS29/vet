package com.vet.web.app.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseHandler {

    public ResponseEntity<Response> successResponse(String message) {
        Response response =
                new Response(HttpStatus.OK, message, "SUCCESSFUL", LocalDateTime.now());

        return new ResponseEntity<Response>(response, null,HttpStatus.CREATED);
    }

}
