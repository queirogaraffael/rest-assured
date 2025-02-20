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
        Usuario usuario = new Usuario(null, usuarioCreateDTO.getNome(), usuarioCreateDTO.getEmail(), usuarioCreateDTO.getSenha(), usuarioCreateDTO.getRole());
        usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }


    public UsuarioResponseDTO getUsuarioByEmail(String email){
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole());

    }


}
