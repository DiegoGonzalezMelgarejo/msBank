package com.bank.diego.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateClienteDto {

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;
    @NotNull(message = "la clave es obligatorio")
    private String clave;
    @NotNull(message = "la direccion es obligatorio")
    private String direccion;
    @NotNull(message = "El telefono es obligatorio")
    private String telefono;
    @NotNull(message = "El nombre es obligatorio")
    private String identificacion;
    @NotNull(message = "El genero es obligatorio")
    private String genero;
    @NotNull(message = "La edad es obligatorio")
    private Integer edad;
    private boolean estado;
}
