package com.bank.diego.dto.movimiento;

import com.bank.diego.dto.cuenta.CuentaDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MovimientoDto {
    private Long movimientoId;
    @DateTimeFormat(pattern="yyyy/MM/dd")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fecha;
    private String tipoMovimiento;
    private Long valor;
    private Long saldo;

    private CuentaDto cuenta;
}
