package com.example.entities.usuario;

public enum UserRole {
    ADMIN("Administrador", "true"),
    USER("Usuário", "false");

    private final String displayName;
    private final String apiValue;

    UserRole(String displayName, String apiValue) {
        this.displayName = displayName;
        this.apiValue = apiValue;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getApiValue() {
        return apiValue;
    }

    public static UserRole fromApiValue(String apiValue) {
        for (UserRole role : UserRole.values()) {
            if (role.getApiValue().equalsIgnoreCase(apiValue)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Valor de API de Role desconhecido: " + apiValue);
    }
}