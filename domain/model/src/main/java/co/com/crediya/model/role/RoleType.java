package co.com.crediya.model.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

    CLIENTE("Cliente", "Usuario cliente del sistema"),
    ADMINISTRADOR("Administrador", "Usuario administrador con privilegios completos");

    private final String displayName;
    private final String description;

    public boolean isAdmin() {
        return this == ADMINISTRADOR;
    }

    public boolean isClient() {
        return this == CLIENTE;
    }
}
