package com.bank.diego.dto.cuenta;

import com.bank.diego.dto.cliente.ClienteDto;
import lombok.Data;

@Data
public class CuentaDto {
    private Long cuentaId;
    private Long numeroCuenta;
    private String tipoCuenta;
    private Long saldoInicial;
    private String estado;
    private ClienteDto cliente;
    private Long saldoActual;
}
