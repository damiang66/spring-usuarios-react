package com.damian.backen.usuarios.app.usuariosapp.service;

import java.util.List;
import java.util.Optional;

import com.damian.backen.usuarios.app.usuariosapp.endidad.Usuario;

public interface UsuarioService {
    public List<Usuario> findAll();
    public Optional<Usuario>findById(Long id);
    public Usuario save (Usuario usuario);
    public void delete(Long id);
}
