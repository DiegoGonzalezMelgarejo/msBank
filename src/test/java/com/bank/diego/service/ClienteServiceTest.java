package com.bank.diego.service;

import com.bank.diego.dto.cliente.ClienteDto;
import com.bank.diego.dto.cliente.CreateClienteDto;
import com.bank.diego.entities.Cliente;
import com.bank.diego.exception.ClienteException;
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

public class ClienteServiceTest {

    private ClienteServiceImpl clienteService;
    private IClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        clienteRepository = mock(IClienteRepository.class);
        clienteService = new ClienteServiceImpl(clienteRepository);
    }

    @Test
    public void save_ok() {
        CreateClienteDto createClienteDto = getCreateClienteDto();
        when(clienteRepository.save(any())).thenReturn(getCliente());
        ClienteDto clienteDto = clienteService.save(createClienteDto);
        Assertions.assertNotNull(clienteDto);
    }

    @Test
    public void save_exception() {
        CreateClienteDto createClienteDto = getCreateClienteDto();
        when(clienteRepository.save(any())).thenThrow(new ClienteException(Constante.ERROR_AL_GUARDAR_USUARIO));

        ClienteException clienteException = Assertions.assertThrows(ClienteException.class, () -> {
            ClienteDto clienteDto = clienteService.save(createClienteDto);

        });
        Assertions.assertNotNull(clienteException);
    }

    @Test
    public void findAll_ok() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(getCliente());
        when(clienteRepository.findAll()).thenReturn(clientes);
        List<ClienteDto> clienteDtos = clienteService.findAll();
        Assertions.assertNotNull(clienteDtos);
    }

    @Test
    public void findbyId_ok() {
        Optional<Cliente> cliente = Optional.of(getCliente());
        when(clienteRepository.findById(any())).thenReturn(cliente);
        ClienteDto c = clienteService.findBById(1l);
        Assertions.assertNotNull(c);
    }


    @Test
    public void findbyId_exception() {
        Optional<Cliente> cliente = Optional.empty();
        when(clienteRepository.findById(any())).thenReturn(cliente);

        ClienteException clienteException = Assertions.assertThrows(ClienteException.class, () -> {
            ClienteDto c = clienteService.findBById(1l);

        });
        Assertions.assertNotNull(clienteException);
    }

    @Test
    public void delete_ok() {
        Optional<Cliente> cliente = Optional.of(getCliente());
        when(clienteRepository.findById(any())).thenReturn(cliente);
        String txt = clienteService.delete(1l);
        Assertions.assertNotNull(txt);

    }

    @Test
    public void delete_exception() {
        Optional<Cliente> cliente = Optional.empty();
        when(clienteRepository.findById(any())).thenReturn(cliente);

        ClienteException clienteException = Assertions.assertThrows(ClienteException.class, () -> {
            String c = clienteService.delete(1l);

        });
        Assertions.assertNotNull(clienteException);
    }

    @Test
    public void update() {
        Optional<Cliente> cliente = Optional.of(getCliente());
        when(clienteRepository.findById(any())).thenReturn(cliente);

        when(clienteRepository.save(any())).thenReturn(getCliente());
        ClienteDto clienteDto=clienteService.update(getClienteDto());
        Assertions.assertNotNull(clienteDto);


    }
    @Test
    public void update_exception() {
        Optional<Cliente> cliente = Optional.empty();
        when(clienteRepository.findById(any())).thenReturn(cliente);
        when(clienteRepository.save(any())).thenReturn(getCliente());
        ClienteException clienteException = Assertions.assertThrows(ClienteException.class, () -> {
            ClienteDto c = clienteService.update(getClienteDto());

        });
        Assertions.assertNotNull(clienteException);
    }
    private ClienteDto getClienteDto() {
        return new ClienteDto();
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

    private CreateClienteDto getCreateClienteDto() {
        CreateClienteDto createClienteDto = new CreateClienteDto();
        createClienteDto.setTelefono("");
        createClienteDto.setGenero("");
        createClienteDto.setEstado(true);
        createClienteDto.setEdad(22);
        createClienteDto.setDireccion("");
        createClienteDto.setClave("");
        return createClienteDto;
    }

}
