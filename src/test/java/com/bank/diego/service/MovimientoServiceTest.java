package com.bank.diego.service;

import com.bank.diego.dto.cuenta.CuentaDto;
import com.bank.diego.dto.movimiento.CreateMovimientoDto;
import com.bank.diego.dto.movimiento.MovimientoDto;
import com.bank.diego.entities.Cuenta;
import com.bank.diego.entities.Movimientos;
import com.bank.diego.exception.CuentaException;
import com.bank.diego.exception.MovimientoException;
import com.bank.diego.repository.CuentaRepository;
import com.bank.diego.repository.IMovimientoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovimientoServiceTest {

    private CuentaRepository cuentaRepository;
    private IMovimientoRepository movimientoRepository;

    private MovimientoService movimientoService;

    @BeforeEach
    public void setUp() {
        cuentaRepository=mock(CuentaRepository.class);
        movimientoRepository=mock(IMovimientoRepository.class);
        movimientoService=new MovimientoService(movimientoRepository,cuentaRepository);

    }

    @Test
    public void save_ok(){
    when(cuentaRepository.findById(any())).thenReturn(Optional.of(getCuenta()));
    when(cuentaRepository.save(any())).thenReturn(getCuenta());
    when(movimientoRepository.save(any())).thenReturn(getMovimiento());
    CreateMovimientoDto createMovimientoDto=getCreateMovimientoDto();
    createMovimientoDto.setValor(100l);
        MovimientoDto movimientoDto=movimientoService.save(createMovimientoDto);
        Assertions.assertNotNull(movimientoDto);
    }
    @Test
    public void save_error_cuenta(){
        when(cuentaRepository.findById(any())).thenReturn(Optional.empty());
        when(cuentaRepository.save(any())).thenReturn(getCuenta());
        when(movimientoRepository.save(any())).thenReturn(getMovimiento());
        CuentaException cuentaException=Assertions.assertThrows(CuentaException.class, () -> {
            MovimientoDto movimientoDto=movimientoService.save(getCreateMovimientoDto());

        });
        Assertions.assertNotNull(cuentaException);


    }
    @Test
    public void save_error_saldo(){
        when(cuentaRepository.findById(any())).thenReturn(Optional.of(getCuenta()));
        when(cuentaRepository.save(any())).thenReturn(getCuenta());
        when(movimientoRepository.save(any())).thenReturn(getMovimiento());
        MovimientoException movimientoException=Assertions.assertThrows(MovimientoException.class, () -> {
            MovimientoDto movimientoDto=movimientoService.save(getCreateMovimientoDto());

        });
        Assertions.assertNotNull(movimientoException);


    }
    @Test
    public void findById(){
        when(movimientoRepository.findById(any())).thenReturn(Optional.of(getMovimiento()));
        MovimientoDto movimientoDto=movimientoService.finById(1L);
        Assertions.assertNotNull(movimientoDto);
    }
    @Test
    public void findById_error(){
        when(movimientoRepository.findById(any())).thenReturn(Optional.empty());
        MovimientoException movimientoException=Assertions.assertThrows(MovimientoException.class, () -> {
            MovimientoDto movimientoDto=movimientoService.finById(1l);

        });
        Assertions.assertNotNull(movimientoException);
    }

    @Test
    public void findall_ok(){
        List<Movimientos>movimientos=new ArrayList<>();
        movimientos.add(getMovimiento());
        when(movimientoRepository.findAll()).thenReturn(movimientos);
        List<MovimientoDto>movimientoDtos=movimientoService.findAll();
        Assertions.assertNotNull(movimientoDtos);
    }
    private Movimientos getMovimiento(){
        Movimientos movimientos=new Movimientos();
        return movimientos;
    }


    private CreateMovimientoDto getCreateMovimientoDto(){
        CreateMovimientoDto createMovimientoDto= new CreateMovimientoDto();
        createMovimientoDto.setCuentaId(1l);
        createMovimientoDto.setValor(-100000000l);
        return createMovimientoDto;
    }

    private Cuenta getCuenta(){
        Cuenta cuenta= new Cuenta();
        cuenta.setCuentaId(1l);
        cuenta.setSaldoActual(10000l);
        cuenta.setNumeroCuenta(22l);
        cuenta.setSaldoInicial(10000l);
        return  cuenta;
    }
}
