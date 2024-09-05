package org.isemper.com.institutosemper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.isemper.com.institutosemper.entity.User;
import org.isemper.com.institutosemper.repository.RoleRepository;
import org.isemper.com.institutosemper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectNode> data() {
        // Crear un objeto JSON utilizando ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        Optional<User> user = userRepository.findById(1L);

        // Añadir propiedades al objeto JSON
        json.put("hello", user.get().getEmail());

        // Retornar la respuesta con el JSON y el estado 200 OK
        return ResponseEntity.ok(json);
    }

}
