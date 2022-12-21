package com.bank.diego.service;

import com.bank.diego.dto.ClienteDto;
import com.bank.diego.dto.CreateClienteDto;
import com.bank.diego.entities.Cliente;
import com.bank.diego.exception.ClienteException;
import com.bank.diego.mapper.ClienteMapper;
import com.bank.diego.mapper.CreateClienteMapper;
import com.bank.diego.repository.IClienteRepository;
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
            throw  new ClienteException("El documento de identidad debe ser unico");
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
            throw new ClienteException("No existe cliente por ese id");
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
