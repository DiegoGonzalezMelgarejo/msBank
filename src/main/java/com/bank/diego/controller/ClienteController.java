package com.bank.diego.controller;

import com.bank.diego.dto.ClienteDto;
import com.bank.diego.dto.CreateClienteDto;
import com.bank.diego.service.IClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {


    @Autowired
    private IClienteService clienteService;
    @ApiOperation(value = "Crear cliente")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )
    @PostMapping
    public ResponseEntity<?> save( @Valid @RequestBody CreateClienteDto createClienteDto){
        return  new ResponseEntity<>(clienteService.save(createClienteDto),HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listar clientes")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )
    @GetMapping()
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(clienteService.findAll(),HttpStatus.OK);
    }
    @ApiOperation(value = "borrar clientes")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@RequestParam Long id){
        return new ResponseEntity<>(clienteService.delete(id),HttpStatus.OK);
    }
    @ApiOperation(value = "actualizar clientes")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )
    @PatchMapping()
    public   ResponseEntity<?> update(@RequestBody ClienteDto clienteDto){
        return new ResponseEntity<>(clienteService.update(clienteDto),HttpStatus.OK);
    }
}
