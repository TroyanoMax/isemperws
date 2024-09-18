package org.isemper.com.institutosemper.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.isemper.com.institutosemper.exception.GeneralServiceException;
import org.isemper.com.institutosemper.security.model.common.GenericResponse;
import org.isemper.com.institutosemper.security.model.dto.AuthRequest;
import org.isemper.com.institutosemper.security.model.dto.AuthResponse;
import org.isemper.com.institutosemper.security.service.UserDetailsServiceImpl;
import org.isemper.com.institutosemper.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
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
        var genericResponse = new GenericResponse<AuthResponse>();
        genericResponse.setBody(authResponse);

        return ResponseEntity.ok(genericResponse);
    }
    
}
