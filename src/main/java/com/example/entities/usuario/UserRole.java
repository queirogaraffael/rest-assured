package com.example.entities.usuario;

public enum UserRole {
    ADMIN("Administrador", "Acesso total ao sistema"),
    USER("Usuário", "Acesso limitado");

    private final String displayName;
    private final String description;

    UserRole(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}

