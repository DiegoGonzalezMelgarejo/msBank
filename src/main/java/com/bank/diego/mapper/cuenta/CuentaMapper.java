package com.bank.diego.mapper.cuenta;

import com.bank.diego.dto.cuenta.CuentaDto;
import com.bank.diego.entities.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CuentaMapper {

    CuentaMapper MAPPER= Mappers.getMapper(CuentaMapper.class);

    CuentaDto cuentaToCuentaDto(Cuenta cuenta);
    Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto);
}
