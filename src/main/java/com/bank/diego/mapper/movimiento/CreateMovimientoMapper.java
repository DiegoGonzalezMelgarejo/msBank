package com.bank.diego.mapper.movimiento;

import com.bank.diego.dto.movimiento.CreateMovimientoDto;
import com.bank.diego.entities.Movimientos;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateMovimientoMapper {

    CreateMovimientoMapper MAPPER= Mappers.getMapper(CreateMovimientoMapper.class);
    Movimientos createMovimientoDtoToMovimiento(CreateMovimientoDto createMovimientoDto);
}
