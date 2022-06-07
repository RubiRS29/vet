package com.vet.web.app.exceptions;

public class InternalServerError extends RuntimeException{

    public InternalServerError(String message) {
        super(message);
    }

}
