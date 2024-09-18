package org.isemper.com.institutosemper.security.exception;

import org.isemper.com.institutosemper.security.model.common.GenericResponse;
import org.isemper.com.institutosemper.security.model.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<GenericResponse<AuthResponse>>  handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(
                        new GenericResponse<>(
                                !HttpStatus.FORBIDDEN.isError(),
                                HttpStatus.FORBIDDEN.value(),
                                HttpStatus.FORBIDDEN.toString(),
                                HttpStatus.FORBIDDEN.getReasonPhrase(),
                                ex.getMessage(),
                                null
                        ));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GenericResponse<AuthResponse>> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(
                        new GenericResponse<>(
                                !HttpStatus.FORBIDDEN.isError(),
                                HttpStatus.FORBIDDEN.value(),
                                HttpStatus.FORBIDDEN.toString(),
                                HttpStatus.FORBIDDEN.getReasonPhrase(),
                                ex.getMessage(),
                                null
                        ));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<GenericResponse<AuthResponse>> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(
                        new GenericResponse<>(
                                !HttpStatus.FORBIDDEN.isError(),
                                HttpStatus.FORBIDDEN.value(),
                                HttpStatus.FORBIDDEN.toString(),
                                HttpStatus.FORBIDDEN.getReasonPhrase(),
                                ex.getMessage(),
                                null
                        ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GenericResponse<AuthResponse>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new GenericResponse<>(
                                !HttpStatus.BAD_REQUEST.isError(),
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.toString(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                ex.getMessage(),
                                null
                        ));
    }

}
