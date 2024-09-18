package org.isemper.com.institutosemper.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.isemper.com.institutosemper.exception.GeneralServiceException;
import org.isemper.com.institutosemper.model.dto.UserDTO;
import org.isemper.com.institutosemper.model.dto.UserResponse;
import org.isemper.com.institutosemper.security.model.common.GenericResponse;
import org.isemper.com.institutosemper.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    @Operation(summary = "Dar de alta un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User SignUp",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "No Token",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GeneralServiceException.class)
                    ))}
    )
    @PostMapping(path = "/sign-up",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponse<UserResponse>> userSingup(
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
