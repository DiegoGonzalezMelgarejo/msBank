package com.bank.diego.mapper.movimiento;

import com.bank.diego.dto.movimiento.MovimientoDto;
import com.bank.diego.entities.Movimientos;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovimientoMapper {

    MovimientoMapper MAPPER= Mappers.getMapper(MovimientoMapper.class);

    MovimientoDto movimientoToMovimientoDto(Movimientos movimiento);
    Movimientos movimientoDtoTomovimiento(MovimientoDto movimientoDto);

}
