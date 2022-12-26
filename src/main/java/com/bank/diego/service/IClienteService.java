package com.bank.diego.service;

import com.bank.diego.dto.cliente.ClienteDto;
import com.bank.diego.dto.cliente.CreateClienteDto;

import java.util.List;

public interface IClienteService {

    ClienteDto save(CreateClienteDto createClienteDto);
    List<ClienteDto> findAll();
    ClienteDto findBById(Long id);
    String delete(Long id);
    ClienteDto update(ClienteDto clienteDto);
}
