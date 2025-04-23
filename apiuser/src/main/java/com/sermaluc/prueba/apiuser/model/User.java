package com.sermaluc.prueba.apiuser.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USUARIOS") 
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    @Column(unique = true)
    private String email;

    @NotNull(message = "La contraseña es obligatoria")
    @Pattern(regexp = "${user.password.regex}", message = "La contraseña no cumple con el formato requerido")
    private String password;

    private LocalDateTime creado;

    private LocalDateTime modificado;

    private LocalDateTime ultimoLogin;

    private String token;

    private boolean estaActivo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Telefono> telefonos;

}
