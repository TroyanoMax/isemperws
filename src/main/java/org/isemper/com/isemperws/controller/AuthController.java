package org.isemper.com.isemperws.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.isemper.com.isemperws.exception.GeneralServiceException;
import org.isemper.com.isemperws.security.model.common.GenericResponse;
import org.isemper.com.isemperws.security.model.dto.AuthRequest;
import org.isemper.com.isemperws.security.model.dto.AuthResponse;
import org.isemper.com.isemperws.security.service.UserDetailsServiceImpl;
import org.isemper.com.isemperws.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {


    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Get JWT Token")
    @PostMapping(path = "/authenticate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponse<AuthResponse>> createAuthenticationToken(
            @RequestBody AuthRequest authRequest
    ) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);

            var authResponse = new AuthResponse(null, jwt);

            return ResponseEntity.ok(
                    new GenericResponse<>(
                            !HttpStatus.OK.isError(),
                            HttpStatus.OK.value(),
                            null,
                            null,
                            HttpStatus.OK.getReasonPhrase(),
                            authResponse
                    )
            );

        } catch (GeneralServiceException e) {
            log.warn(e.getMessage(), e);
            return new ResponseEntity<>(
                    new GenericResponse<>(
                            !HttpStatus.INTERNAL_SERVER_ERROR.isError(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                            e.getCause().getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
