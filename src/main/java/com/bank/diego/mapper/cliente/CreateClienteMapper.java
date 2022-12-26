package com.bank.diego.mapper.cliente;

import com.bank.diego.dto.cliente.CreateClienteDto;
import com.bank.diego.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateClienteMapper {
    CreateClienteMapper MAPPER= Mappers.getMapper(CreateClienteMapper.class);
    Cliente createClienteDtoToCliente(CreateClienteDto createClienteDTO);
    CreateClienteDto clienteToCreateClienteDto(Cliente cliente);

}
