package com.example.services;

import com.example.entities.usuario.Usuario;
import com.example.entities.usuario.UsuarioCreateDTO;
import com.example.entities.usuario.UsuarioResponseDTO;
import com.example.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO create(UsuarioCreateDTO usuarioCreateDTO) {
        boolean isAdmin = Boolean.parseBoolean(usuarioCreateDTO.getAdministrador());

        Usuario usuario = new Usuario(
                usuarioCreateDTO.getNome(),
                usuarioCreateDTO.getEmail(),
                usuarioCreateDTO.getPassword(),
                isAdmin
        );
        usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuario.getId().toString(),
                usuario.getNome(),
                usuario.getEmail(),
                String.valueOf(usuario.isAdministrador())
        );
    }

    public UsuarioResponseDTO getUsuarioByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        return new UsuarioResponseDTO(
                usuario.getId().toString(),
                usuario.getNome(),
                usuario.getEmail(),
                String.valueOf(usuario.isAdministrador())
        );
    }
}