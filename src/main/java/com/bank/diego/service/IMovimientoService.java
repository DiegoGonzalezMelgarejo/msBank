package com.bank.diego.service;

import com.bank.diego.dto.movimiento.CreateMovimientoDto;
import com.bank.diego.dto.movimiento.MovimientoDto;
import com.bank.diego.dto.movimiento.ReporteDto;

import java.util.Date;
import java.util.List;

public interface IMovimientoService {


    MovimientoDto save(CreateMovimientoDto createMovimientoDto);
    MovimientoDto finById(Long id);

    List<MovimientoDto> findAll();

    List<MovimientoDto> reporte(ReporteDto fechas);
}
