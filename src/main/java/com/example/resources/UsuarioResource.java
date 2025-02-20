package com.example.resources;

import com.example.entities.usuario.UsuarioCreateDTO;
import com.example.entities.usuario.UsuarioResponseDTO;
import com.example.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.create(usuarioCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioByEmail(@PathVariable String email) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.getUsuarioByEmail(email);
        return ResponseEntity.ok(usuarioResponseDTO);
    }
}
