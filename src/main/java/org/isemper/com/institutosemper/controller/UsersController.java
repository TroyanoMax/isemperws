package org.isemper.com.institutosemper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.isemper.com.institutosemper.exception.GeneralServiceException;
import org.isemper.com.institutosemper.model.dto.GenericResponseDTO;
import org.isemper.com.institutosemper.model.dto.UserDTO;
import org.isemper.com.institutosemper.model.dto.UserResponse;
import org.isemper.com.institutosemper.service.UsersService;
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
    public ResponseEntity<GenericResponseDTO<UserResponse>> userSingup(
            @Parameter(description = "Datos del usuario")
            @RequestBody UserDTO body
    ){
        try {

            return ResponseEntity.ok(
                    new GenericResponseDTO<>(
                            CommonController.SUCCESS,
                            CommonController.HTTP_SUCCESS,
                            null,
                            null,
                            CommonController.SUCCESS_MESSAGE,
                            usersService.userSignup(body)
                    )
            );

        } catch (GeneralServiceException e) {
            log.warn(e.getMessage(), e);
            return new ResponseEntity<>(
                    new GenericResponseDTO<>(
                            ERROR,
                            HTTP_APP_FAILURE,
                            null,
                            e.getMessage(),
                            null,
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
