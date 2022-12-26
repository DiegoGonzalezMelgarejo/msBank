package com.bank.diego.dto.cuenta;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateCuentaDto {
    @NotNull(message = "El id del cliente  es obligatorio")
    private Long clienteId;
    @NotNull(message = "El numero de cuenta  es obligatorio")
    private Long numeroCuenta;
    @NotNull(message = "El tipo de cuenta  es obligatorio")
    private String tipoCuenta;
    @NotNull(message = "El saldo inicial es obligatorio")
    private Long saldoInicial;
    private boolean estado;


}
