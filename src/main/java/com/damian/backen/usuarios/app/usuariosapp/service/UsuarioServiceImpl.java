package com.damian.backen.usuarios.app.usuariosapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damian.backen.usuarios.app.usuariosapp.endidad.Usuario;
import com.damian.backen.usuarios.app.usuariosapp.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepositorio repositorio;

    @Override
    public List<Usuario> findAll() {
       return repositorio.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
       return repositorio.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
     return repositorio.save(usuario);
    }

    @Override
    public void delete(Long id) {
      repositorio.deleteById(id);
    }
    
}
