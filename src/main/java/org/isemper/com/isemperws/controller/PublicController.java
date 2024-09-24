package org.isemper.com.isemperws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import org.isemper.com.isemperws.security.model.entity.RoleEntity;
import org.isemper.com.isemperws.security.model.entity.UserEntity;
import org.isemper.com.isemperws.security.repository.RoleRepository;
import org.isemper.com.isemperws.security.repository.UserRepository;
import org.isemper.com.isemperws.security.repository.UserRoleRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public PublicController(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Operation(summary = "Get First User for Test")
    @GetMapping(value = "/get-first", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> data() {
        // Crear un objeto JSON utilizando ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        Optional<UserEntity> user = userRepository.findById(1L);

        Optional<RoleEntity> role = roleRepository.findById(1L);
        List<UserEntity> users = new ArrayList<>();
        role.ifPresent(
                roleEntity -> {
                    users.addAll(roleEntity.getUsers());
                }
        );

        user.ifPresent(
                userEntity -> {
                    userEntity.setCantAcc(userEntity.getCantAcc() + 1);
                    userRepository.save(userEntity);
                }
        );

        // Retornar la respuesta con el JSON y el estado 200 OK
        return ResponseEntity.ok(user.get());
    }

}
