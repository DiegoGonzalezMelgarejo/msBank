package com.bank.diego.controller;

import com.bank.diego.dto.movimiento.CreateMovimientoDto;
import com.bank.diego.dto.movimiento.ReporteDto;
import com.bank.diego.service.IMovimientoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private IMovimientoService movimientoService;
    @ApiOperation(value = "Crear movimiento")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CreateMovimientoDto createMovimientoDto){
        return new ResponseEntity<>(movimientoService.save(createMovimientoDto), HttpStatus.CREATED);
    }
    @ApiOperation(value = "listar movimientos")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )

    @GetMapping
    public ResponseEntity<?> findAll(){
        return  new ResponseEntity<>(movimientoService.findAll(), HttpStatus.OK);
    }
    @ApiOperation(value = "listar movimiento")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@RequestParam Long id){
        return  new ResponseEntity<>(movimientoService.finById(id), HttpStatus.OK);
    }
    @ApiOperation(value = "listar movimientos")
    @ApiResponses(
            value = {
                    @ApiResponse(code=200,message="Request completed successfully")
            }
    )

    @PostMapping("/reporte")
    public ResponseEntity<?> report(@RequestBody ReporteDto fechas){
        return  new ResponseEntity<>(movimientoService.reporte(fechas), HttpStatus.OK);
    }

}
