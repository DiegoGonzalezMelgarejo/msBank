package com.bank.diego.controller;

import com.bank.diego.dto.cuenta.CreateCuentaDto;
import com.bank.diego.dto.cuenta.CuentaDto;
import com.bank.diego.service.ICuentaService;
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
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private ICuentaService cuentaService;

    @ApiOperation(value = "Crear cuenta")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody  CreateCuentaDto createCuentaDto){
        return new ResponseEntity<>(cuentaService.save(createCuentaDto), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Obtener cuenta")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@RequestParam Long id){
        return new ResponseEntity<>(cuentaService.findById(id),HttpStatus.OK);
    }
    @ApiOperation(value = "Obtener cuentas")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )
    @GetMapping
    public ResponseEntity<?>findAll(){
        return new ResponseEntity<>(cuentaService.findAll(),HttpStatus.OK);
    }
    @ApiOperation(value = "Eliminar cuentas")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@RequestParam Long id){
        return new ResponseEntity<>(cuentaService.delete(id),HttpStatus.OK);
    }

    @ApiOperation(value = "actualizar cuentas")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )
    @PatchMapping()
    public ResponseEntity<?>update(@RequestParam CuentaDto cuenta){
        return new ResponseEntity<>(cuentaService.update(cuenta),HttpStatus.OK);
    }
}
