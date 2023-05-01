package com.damian.backen.usuarios.app.usuariosapp.controlador;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.damian.backen.usuarios.app.usuariosapp.endidad.Usuario;
import com.damian.backen.usuarios.app.usuariosapp.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControler {
    @Autowired
    private UsuarioService service;
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        Optional<Usuario> r = service.findById(id);
        if(r.isPresent()){
           return ResponseEntity.ok().body(r.get());
        }
        return ResponseEntity.notFound().build();

    }
    @PostMapping
    public ResponseEntity<?>save (@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>editar(@RequestBody Usuario usuario,@PathVariable Long id){
        Optional<Usuario> r = service.findById(id);
        if (r.isPresent()){
            Usuario usuarioDb = r.get();
            usuarioDb.setUsername(usuario.getUsername());
            usuarioDb.setEmail(usuario.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuarioDb));
        }
        return ResponseEntity.notFound().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Usuario> r = service.findById(id);
        if (r.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    
}
