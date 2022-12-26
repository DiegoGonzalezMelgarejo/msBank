package com.bank.diego.dto.movimiento;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CreateMovimientoDto {
    private Long cuentaId;
    @DateTimeFormat(pattern="yyyy/MM/dd")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fecha;
    private Long valor;

}
