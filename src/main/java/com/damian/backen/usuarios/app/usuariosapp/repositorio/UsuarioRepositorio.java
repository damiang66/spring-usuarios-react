package com.damian.backen.usuarios.app.usuariosapp.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.damian.backen.usuarios.app.usuariosapp.endidad.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
    
}
