package com.vet.web.app.exceptions;

public class ForbiddenException extends RuntimeException{

    public ForbiddenException(String message) {
        super(message);
    }

}
