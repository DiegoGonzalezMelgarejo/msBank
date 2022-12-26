package com.bank.diego.mapper.cuenta;

import com.bank.diego.dto.cuenta.CreateCuentaDto;
import com.bank.diego.dto.cuenta.CuentaDto;
import com.bank.diego.entities.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateCuentaMapper {

    CreateCuentaMapper MAPPER= Mappers.getMapper(CreateCuentaMapper.class);
    Cuenta  createCuentaDtoTocuenta(CreateCuentaDto  createCuentaDto);
    CuentaDto cuentaToCuentaDto(Cuenta cuenta);
}
