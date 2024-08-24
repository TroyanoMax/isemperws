package org.isemper.com.institutosemper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectNode> data() {
        // Crear un objeto JSON utilizando ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        // Añadir propiedades al objeto JSON
        json.put("hello", "world");

        // Retornar la respuesta con el JSON y el estado 200 OK
        return ResponseEntity.ok(json);
    }

}
