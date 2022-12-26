package com.bank.diego.dto.cliente;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClienteDto {
    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;
    @NotNull(message = "El nombre es obligatorio")
    private String nombre;
    @NotNull(message = "la clave es obligatoria")
    private String clave;
    @NotNull(message = "la direccion es obligatoria")
    private String direccion;
    @NotNull(message = "El telefono es obligatorio")
    private String telefono;
    @NotNull(message = "La identificacion es obligatoria")
    private String identificacion;
    @NotNull(message = "El genero es obligatorio")
    private String genero;
    @NotNull(message = "La edad es obligatorio")
    private Integer edad;
    private boolean estado;
}
