package com.bank.diego.mapper.cliente;

import com.bank.diego.dto.cliente.ClienteDto;
import com.bank.diego.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {
    ClienteMapper clienteMapper= Mappers.getMapper(ClienteMapper.class);
    ClienteDto clienteToClienteDto(Cliente cliente);

    Cliente clienteDtoTocliente(ClienteDto clienteDto);

}
