package org.isemper.com.institutosemper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.isemper.com.institutosemper.entity.RoleEntity;
import org.isemper.com.institutosemper.entity.UserEntity;
import org.isemper.com.institutosemper.repository.RoleRepository;
import org.isemper.com.institutosemper.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class LoginController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public LoginController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserEntity>> data() {
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

        // Retornar la respuesta con el JSON y el estado 200 OK
        return ResponseEntity.ok(users);
    }

}
