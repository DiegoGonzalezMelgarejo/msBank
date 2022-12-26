package com.bank.diego.service;

import com.bank.diego.dto.cuenta.CreateCuentaDto;
import com.bank.diego.dto.cuenta.CuentaDto;

import java.util.List;

public interface ICuentaService {

    CuentaDto save(CreateCuentaDto createCuentaDto);

    CuentaDto findById(Long id);


    String delete(Long id);

    CuentaDto update(Long id,CreateCuentaDto createCuentaDto);

    List<CuentaDto> findAll();
}
