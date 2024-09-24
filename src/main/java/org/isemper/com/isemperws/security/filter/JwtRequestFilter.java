package org.isemper.com.isemperws.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.isemper.com.isemperws.security.model.common.GenericResponse;
import org.isemper.com.isemperws.security.model.dto.AuthResponse;
import org.isemper.com.isemperws.security.service.UserDetailsServiceImpl;
import org.isemper.com.isemperws.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Authorization JwtRequestFilter.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    /** * UserDetailServiceImpl */
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    /** * JwtUtil Service. */
    private final JwtUtil jwtUtil;

    /**
     * Constructor.
     * @param userDetailsServiceImpl userDetailServiceImpl
     * @param jwtUtil jwtUtil
     */
    @Autowired
    public JwtRequestFilter(UserDetailsServiceImpl userDetailsServiceImpl, JwtUtil jwtUtil) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Filtro interno.
     * @param request request
     * @param response response
     * @param chain chain
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain chain
    ) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                jwt = authorizationHeader.substring(7);
                username = jwtUtil.extractUsername(jwt);
            } catch (ExpiredJwtException e) {
                sendErrorResponse(
                        response,
                        HttpStatus.UNAUTHORIZED.isError(),
                        HttpStatus.UNAUTHORIZED.value(),
                        HttpStatus.UNAUTHORIZED.toString(),
                        e.getMessage(),
                        HttpStatus.UNAUTHORIZED.getReasonPhrase()
                );
                return;
            } catch (Exception e) {
                sendErrorResponse(
                        response,
                        HttpStatus.BAD_REQUEST.isError(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.toString(),
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase()
                );
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                usernamePasswordAuthenticationToken
                        .setDetails(
                                new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                        );
                SecurityContextHolder.getContext()
                        .setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                sendErrorResponse(
                        response,
                        HttpStatus.FORBIDDEN.isError(),
                        HttpStatus.FORBIDDEN.value(),
                        HttpStatus.FORBIDDEN.toString(),
                        "Invalid JWT token",
                        HttpStatus.FORBIDDEN.getReasonPhrase()
                );
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * Response.
     * @param response response
     * @param isError isError
     * @param httpStatus httpStatus
     * @param errorCode errorCode
     * @param errorMessage errorMenssage
     * @param message mensaje
     * @throws IOException exception
     */
    private void sendErrorResponse(
            HttpServletResponse response,
            boolean isError,
            int httpStatus,
            String errorCode,
            String errorMessage,
            String message
    ) throws IOException {
        GenericResponse<AuthResponse> body =
                new GenericResponse<>(
                        isError,
                        httpStatus,
                        errorCode,
                        errorMessage,
                        message,
                        null
                );
        response.setStatus(httpStatus);
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(body));
    }
}
