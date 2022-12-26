package com.bank.diego.service;

import com.bank.diego.dto.cliente.ClienteDto;
import com.bank.diego.dto.cliente.CreateClienteDto;
import com.bank.diego.entities.Cliente;
import com.bank.diego.exception.ClienteException;
import com.bank.diego.mapper.cliente.ClienteMapper;
import com.bank.diego.mapper.cliente.CreateClienteMapper;
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
public class ClienteServiceImpl implements  IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;



    @Override
    public ClienteDto save(CreateClienteDto createClienteDto) {
        try {
            Cliente cliente=clienteRepository.save(CreateClienteMapper.MAPPER.createClienteDtoToCliente(createClienteDto));
            return ClienteMapper.clienteMapper.clienteToClienteDto(cliente);
        } catch (DataIntegrityViolationException e){
            throw  new ClienteException(Constante.ERROR_AL_GUARDAR_USUARIO);
        }

    }

    @Override
    public List<ClienteDto> findAll() {
        List<Cliente> cliente=clienteRepository.findAll();

        return cliente.parallelStream().map(ClienteMapper.clienteMapper::clienteToClienteDto).collect(Collectors.toList());
    }

    @Override
    public ClienteDto findBById(Long id) {

        return ClienteMapper.clienteMapper.clienteToClienteDto(getCliente(id));
    }

    private Cliente getCliente(Long id){
        Optional<Cliente> cliente=clienteRepository.findById(id);
        if(!cliente.isPresent()){
            throw new ClienteException(Constante.NO_EXISTE_CLIENTE);
        }
        return cliente.get();
    }

    @Override
    public String delete(Long id) {
        Cliente cliente=getCliente(id);
        clienteRepository.delete(cliente);

        return "El cliente con numero de documento : " + cliente.getIdentificacion() + " ha sido eliminado";
    }

    @Override
    public ClienteDto update(ClienteDto clienteDto) {
        Cliente cliente= getCliente(clienteDto.getClienteId());
         cliente=ClienteMapper.clienteMapper.clienteDtoTocliente(clienteDto);
        try{

            return ClienteMapper.clienteMapper.clienteToClienteDto(clienteRepository.save(cliente));
        }catch (DataIntegrityViolationException e){
            throw  new ClienteException("El documento de identidad debe ser unico");
        }

    }

}
