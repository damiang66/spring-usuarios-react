package com.damian.backen.usuarios.app.usuariosapp.endidad;

import java.util.List;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 4,max = 8)
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @ManyToMany
    @JoinTable(name="usuarios_roles",joinColumns =@JoinColumn(name="usuario_id"
    ), inverseJoinColumns = @JoinColumn(name="rol_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_Id","rol_id"})}  )
    private List<Rol> roles;
    @Transient
    private boolean admin;
}
