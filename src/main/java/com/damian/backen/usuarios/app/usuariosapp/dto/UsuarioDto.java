package com.damian.backen.usuarios.app.usuariosapp.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private Long id;
    private String username;
    private String email;
    private boolean admin;
    public UsuarioDto(Long id, String username, String email, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.admin= isAdmin;
    }
    
}
