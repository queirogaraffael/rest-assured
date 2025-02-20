package com.example.entities.usuario;

public class UsuarioCreateDTO {
    private String nome;
    private String email;
    private String senha;
    private UserRole role;

    public UsuarioCreateDTO() {
    }

    public UsuarioCreateDTO(String nome, String email, String senha, UserRole role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public UserRole getRole() {
        return role;
    }
}
