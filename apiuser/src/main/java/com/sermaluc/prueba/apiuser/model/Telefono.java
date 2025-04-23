package com.sermaluc.prueba.apiuser.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "\\d{7,10}", message = "El número de teléfono debe tener entre 7 y 10 dígitos")
    private String numeroFono;

    @NotNull(message = "El código de ciudad es obligatorio")
    @Pattern(regexp = "\\d{1,5}", message = "El código de ciudad debe tener entre 1 y 5 dígitos")
    private String codCiudad;

    @NotNull(message = "El código de país es obligatorio")
    @Pattern(regexp = "\\d{1,5}", message = "El código de país debe tener entre 1 y 5 dígitos")
    private String codPais;

    @ManyToOne
    @JoinColumn(name = "user_id") // Relación con la tabla USUARIOS
    private User usuario;

    public Telefono() {
    }

    public Telefono(String numeroFono, String codCiudad, String codPais) {
        this.numeroFono = numeroFono;
        this.codCiudad = codCiudad;
        this.codPais = codPais;
    }

}
