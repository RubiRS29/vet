package com.vet.web.app.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


/**
 * The type Error.
 */
@Data
@Builder
public class Error {

    private HttpStatus httpStatus;
    private String message;
    private String details;
    private LocalDateTime time;

}
