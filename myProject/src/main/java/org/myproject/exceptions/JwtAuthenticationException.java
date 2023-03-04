package org.myproject.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

    private final HttpStatus httpStatus;
    public HttpStatus getHttpStatus(){return httpStatus;}

    public JwtAuthenticationException(String msg, HttpStatus httpStatus, Throwable cause) {
        super(msg, cause);
        this.httpStatus = httpStatus;
    }

    public JwtAuthenticationException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
