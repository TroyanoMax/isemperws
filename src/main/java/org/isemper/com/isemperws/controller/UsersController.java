package org.isemper.com.isemperws.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.isemper.com.isemperws.exception.GeneralServiceException;
import org.isemper.com.isemperws.model.dto.UserDTO;
import org.isemper.com.isemperws.model.dto.UserResponse;
import org.isemper.com.isemperws.model.projection.StudentDataProjection;
import org.isemper.com.isemperws.security.model.common.GenericResponse;
import org.isemper.com.isemperws.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
public class UsersController extends CommonController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Servicio guardar quejas.
     * @param body body
     * @return body respuesta
     */
    @Operation(summary = "User SignUp.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User SignUp",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Bad credentials",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GeneralServiceException.class)
                    ))}
    )
    @PostMapping(path = "/sign-up",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponse<UserResponse>> userSingUp(
            @Parameter(description = "Datos del usuario")
            @RequestBody UserDTO body
    ){
        try {
            return ResponseEntity.ok(
                    new GenericResponse<>(
                            !HttpStatus.OK.isError(),
                            HttpStatus.OK.value(),
                            null,
                            null,
                            HttpStatus.OK.getReasonPhrase(),
                            usersService.userSignup(body)
                    )
            );
        } catch (GeneralServiceException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(
                    new GenericResponse<>(
                            !HttpStatus.INTERNAL_SERVER_ERROR.isError(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                            e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    /**
     * Servicio guardar quejas.
     * @param body body
     * @return body respuesta
     */
    @Operation(summary = "Verfify Student Registration.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Verfify Student Registration.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Bad credentials",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GeneralServiceException.class)
                    ))}
    )
    @GetMapping(path = "/sign-up/verify-student",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponse<StudentDataProjection>> verifyStudentRegistration(
            @Parameter(description = "Codigo")
            @RequestParam("code") String codigo
    ){
        try {
            return ResponseEntity.ok(
                    new GenericResponse<>(
                            !HttpStatus.OK.isError(),
                            HttpStatus.OK.value(),
                            null,
                            null,
                            HttpStatus.OK.getReasonPhrase(),
                            usersService.verifyStudentRegistration(codigo)
                    )
            );
        } catch (GeneralServiceException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(
                    new GenericResponse<>(
                            !HttpStatus.INTERNAL_SERVER_ERROR.isError(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                            e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
