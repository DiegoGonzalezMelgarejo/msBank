package com.bank.diego.service;

import com.bank.diego.dto.movimiento.CreateMovimientoDto;
import com.bank.diego.dto.movimiento.MovimientoDto;
import com.bank.diego.dto.movimiento.ReporteDto;
import com.bank.diego.entities.Cuenta;
import com.bank.diego.entities.Movimientos;
import com.bank.diego.exception.CuentaException;
import com.bank.diego.exception.MovimientoException;
import com.bank.diego.mapper.movimiento.CreateMovimientoMapper;
import com.bank.diego.mapper.movimiento.MovimientoMapper;
import com.bank.diego.repository.CuentaRepository;
import com.bank.diego.repository.IMovimientoRepository;
import com.bank.diego.util.Constante;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovimientoService implements IMovimientoService {

    @Autowired
    private IMovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;
    @Override
    public MovimientoDto save(CreateMovimientoDto createMovimientoDto) {
        Cuenta cuenta=getCuenta(createMovimientoDto.getCuentaId());
        Movimientos movimientos= CreateMovimientoMapper.MAPPER.createMovimientoDtoToMovimiento(createMovimientoDto);
        Long saldo=cuenta.getSaldoActual() + movimientos.getValor();
            if(saldo<0){
                throw new MovimientoException(Constante.SIN_SALDO);
            }
        movimientos.setSaldo(saldo);
        movimientos.setTipoMovimiento(movimientos.getValor()<0? Constante.DEBITO : Constante.CREDITO);
            cuenta.setSaldoActual(saldo);
        cuenta=cuentaRepository.save(cuenta);
        movimientos.setCuenta(cuenta);
        return MovimientoMapper.MAPPER.movimientoToMovimientoDto(movimientoRepository.save(movimientos));

    }

    @Override
    public MovimientoDto finById(Long id) {
        Optional<Movimientos> movimientos=movimientoRepository.findById(id);
        if(movimientos.isEmpty()){
            throw new MovimientoException(Constante.MOVIMIENTO_NO_EXISTE);
        }
        return MovimientoMapper.MAPPER.movimientoToMovimientoDto(movimientos.get());
    }

    @Override
    public List<MovimientoDto> findAll() {
        return movimientoRepository.findAll().parallelStream().map(MovimientoMapper.MAPPER::movimientoToMovimientoDto).collect(Collectors.toList());
    }

    @Override
    public List<MovimientoDto> reporte(ReporteDto fechas) {

        List<Movimientos> movimientos=movimientoRepository.findByFechaBetween(fechas.getFechaInicio(),fechas.getFechaFin());
        return movimientos.parallelStream().map(MovimientoMapper.MAPPER::movimientoToMovimientoDto).collect(Collectors.toList());
    }

    private Cuenta getCuenta(Long id){
        Optional<Cuenta> cuenta=cuentaRepository.findById(id);
        if(cuenta.isEmpty()){
            throw new CuentaException("No existe cuenta que intentas asignar al movimiento");
        }
        return cuenta.get();
    }
}
