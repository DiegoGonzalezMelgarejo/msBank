package com.bank.diego.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public class Persona implements Serializable {
    @Column(name = "identificacion", nullable = false,unique = true)
    private String identificacion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "genero")
    private String genero;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;
}
