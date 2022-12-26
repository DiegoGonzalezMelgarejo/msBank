package com.bank.diego.service;

import com.bank.diego.dto.cuenta.CreateCuentaDto;
import com.bank.diego.dto.cuenta.CuentaDto;
import com.bank.diego.entities.Cliente;
import com.bank.diego.entities.Cuenta;
import com.bank.diego.exception.ClienteException;
import com.bank.diego.exception.CuentaException;
import com.bank.diego.mapper.cuenta.CreateCuentaMapper;
import com.bank.diego.mapper.cuenta.CuentaMapper;
import com.bank.diego.repository.CuentaRepository;
import com.bank.diego.repository.IClienteRepository;
import com.bank.diego.util.Constante;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class CuentaServiceImpl implements ICuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public CuentaDto save(CreateCuentaDto createCuentaDto) {
      Cliente cliente=getCliente(createCuentaDto.getClienteId());
        Cuenta cuenta = CreateCuentaMapper.MAPPER.createCuentaDtoTocuenta(createCuentaDto);
        cuenta.setCliente(cliente);
        cuenta.setSaldoActual(cuenta.getSaldoInicial());
        try {
            return CreateCuentaMapper.MAPPER.cuentaToCuentaDto(cuentaRepository.save(cuenta));

        } catch (DataIntegrityViolationException e) {
            throw new ClienteException(Constante.ERROR_CUENTA);
        }
    }

    private Cliente getCliente(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            throw new ClienteException(Constante.ERROR_ASIGNAR_CLIENTE_CUENTA);
        }
        return cliente.get();
    }

    @Override
    public CuentaDto findById(Long id) {
        return CuentaMapper.MAPPER.cuentaToCuentaDto(getCuenta(id));
    }

    @Override
    public String delete(Long id) {
        Cuenta cuenta=getCuenta(id);
        try {
            cuentaRepository.delete(cuenta);
        }catch (Exception e){
            throw  new CuentaException("Error al eliminar cuenta");
        }
        return "Se ha eliminado cuenta con id " + cuenta.getCuentaId() ;
    }

    @Override
    public CuentaDto update(CuentaDto cuentaDto) {
        Cuenta cuenta=getCuenta(cuentaDto.getCuentaId());
        Cliente cliente=getCliente(cuentaDto.getCliente().getClienteId());
         cuenta= CuentaMapper.MAPPER.cuentaDtoToCuenta(cuentaDto);
        cuenta.setCliente(cliente);
        try {
            return CuentaMapper.MAPPER.cuentaToCuentaDto(cuentaRepository.save(cuenta));
        }catch (DataIntegrityViolationException e) {
            throw new ClienteException(Constante.ERROR_CUENTA);
        }
    }

    private Cuenta getCuenta(Long id){
        Optional<Cuenta> cuenta=cuentaRepository.findById(id);
        if(cuenta.isEmpty()){
            throw  new CuentaException(Constante.ERROR_CUENTA_NO_EXISTE);
        }
        return cuenta.get();
    }

    @Override
    public List<CuentaDto> findAll() {

        return cuentaRepository.findAll().parallelStream().map(CuentaMapper.MAPPER::cuentaToCuentaDto).collect(Collectors.toList());
    }
}
