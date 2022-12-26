package com.bank.diego.service;

import com.bank.diego.dto.cliente.ClienteDto;
import com.bank.diego.dto.cuenta.CreateCuentaDto;
import com.bank.diego.dto.cuenta.CuentaDto;
import com.bank.diego.entities.Cliente;
import com.bank.diego.entities.Cuenta;
import com.bank.diego.exception.ClienteException;
import com.bank.diego.exception.CuentaException;
import com.bank.diego.repository.CuentaRepository;
import com.bank.diego.repository.IClienteRepository;
import com.bank.diego.util.Constante;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CuentaServiceTest {

    private CuentaServiceImpl cuentaService;
    private CuentaRepository cuentaRepository;
    private IClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        cuentaRepository=mock(CuentaRepository.class);
        clienteRepository = mock(IClienteRepository.class);
        cuentaService=new CuentaServiceImpl(cuentaRepository,clienteRepository);
    }

    @Test
    public void save_ok(){
        Optional<Cliente> cliente = Optional.of(getCliente());
        when(clienteRepository.findById(any())).thenReturn(cliente);
        when(cuentaRepository.save(any())).thenReturn(getCuenta());
        CuentaDto cuentaDto= cuentaService.save(getCreateCuentaDto());
        Assertions.assertNotNull(cuentaDto);

    }
    @Test
    public void save_error_cuenta(){
        Optional<Cliente> cliente = Optional.of(getCliente());
        when(clienteRepository.findById(any())).thenReturn(cliente);
        when(cuentaRepository.save(any())).thenThrow(new CuentaException(Constante.ERROR_CUENTA));
        CuentaException cuentaException = Assertions.assertThrows(CuentaException.class, () -> {
            CuentaDto cuentaDto= cuentaService.save(getCreateCuentaDto());
        });
        Assertions.assertNotNull(cuentaException);

    }
    @Test
    public void findById(){
        Optional<Cuenta> cuenta=Optional.of(getCuenta());
        when(cuentaRepository.findById(any())).thenReturn(cuenta);
        CuentaDto cuentaDto=cuentaService.findById(1l);
        Assertions.assertNotNull(cuentaDto);
    }
    @Test
    public void findById_error(){
        Optional<Cuenta> cuenta=Optional.empty();
        when(cuentaRepository.findById(any())).thenReturn(cuenta);
        CuentaException cuentaException = Assertions.assertThrows(CuentaException.class, () -> {
            CuentaDto cuentaDto=cuentaService.findById(1l);

        });
        Assertions.assertNotNull(cuentaException);

    }
    @Test
    public void findAll_ok(){
        List<Cuenta>cuentas=new ArrayList<>();
        cuentas.add(getCuenta());
        when(cuentaRepository.findAll()).thenReturn(cuentas);
        List<CuentaDto> cuentaDtos=cuentaService.findAll();
        Assertions.assertNotNull(cuentaDtos);
    }

    @Test
    public void update(){
        Optional<Cuenta> cuenta=Optional.of(getCuenta());
        Optional<Cliente> cliente=Optional.of(getCliente());
        when(cuentaRepository.findById(any())).thenReturn(cuenta);
        when(clienteRepository.findById(any())).thenReturn(cliente);
        when(cuentaRepository.save(any())).thenReturn(getCuenta());
        CuentaDto cuentaDto= cuentaService.update(getCuentaDto());
        Assertions.assertNotNull(cuentaDto);
    }
    @Test
    public void update_cliente_error(){
        Optional<Cuenta> cuenta=Optional.of(getCuenta());
        Optional<Cliente> cliente=Optional.empty();
        when(cuentaRepository.findById(any())).thenReturn(cuenta);
        when(clienteRepository.findById(any())).thenReturn(cliente);
        when(cuentaRepository.save(any())).thenReturn(getCuenta());

        ClienteException clienteException = Assertions.assertThrows(ClienteException.class, () -> {
            CuentaDto cuentaDto= cuentaService.update(getCuentaDto());
        });
        Assertions.assertNotNull(clienteException);

    }
    @Test
    public void update_cuenta_error(){
        Optional<Cuenta> cuenta=Optional.empty();
        Optional<Cliente> cliente=Optional.of(getCliente());
        when(cuentaRepository.findById(any())).thenReturn(cuenta);
        when(clienteRepository.findById(any())).thenReturn(cliente);
        when(cuentaRepository.save(any())).thenReturn(getCuenta());

        CuentaException cuentaException = Assertions.assertThrows(CuentaException.class, () -> {
            CuentaDto cuentaDto= cuentaService.update(getCuentaDto());
        });
        Assertions.assertNotNull(cuentaException);

    }
    @Test
    public void  delete_ok(){
        Optional<Cuenta> cuenta=Optional.of(getCuenta());
        when(cuentaRepository.findById(any())).thenReturn(cuenta);
        String txt= cuentaService.delete(1l);
    }
    @Test
    public void  delete_error(){
        Optional<Cuenta> cuenta=Optional.empty();
        when(cuentaRepository.findById(any())).thenReturn(cuenta);

        CuentaException cuentaException = Assertions.assertThrows(CuentaException.class, () -> {
            String txt= cuentaService.delete(1l);
        });
        Assertions.assertNotNull(cuentaException);
    }

    private CuentaDto getCuentaDto(){
        CuentaDto cuentaDto=new CuentaDto();
        cuentaDto.setCliente(getClienteDto());

        return  cuentaDto;
    }
    private ClienteDto getClienteDto() {
        ClienteDto clienteDto= new ClienteDto();
        clienteDto.setClienteId(1l);
        return clienteDto;
    }
    @Test
    public void save_error_user(){
        Optional<Cliente> cliente = Optional.empty();

        when(clienteRepository.findById(any())).thenReturn(cliente);
        when(cuentaRepository.save(any())).thenReturn(getCuenta());
        ClienteException clienteException = Assertions.assertThrows(ClienteException.class, () -> {
            CuentaDto cuentaDto= cuentaService.save(getCreateCuentaDto());
        });
        Assertions.assertNotNull(clienteException);

    }

    private CreateCuentaDto getCreateCuentaDto(){
        CreateCuentaDto createCuentaDto= new CreateCuentaDto();
        createCuentaDto.setNumeroCuenta(1l);
        createCuentaDto.setClienteId(1l);
        return createCuentaDto;

    }
    private Cuenta getCuenta(){
        Cuenta cuenta=new Cuenta();
        cuenta.setCuentaId(1l);
        return  cuenta;
    }
    private Cliente getCliente() {
        Cliente c = new Cliente();
        c.setTelefono("");
        c.setGenero("");
        c.setEstado(true);
        c.setEdad(22);
        c.setDireccion("");
        c.setClave("");
        c.setClienteId(1l);
        return c;
    }

}
