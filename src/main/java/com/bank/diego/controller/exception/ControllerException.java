package com.bank.diego.controller.exception;

import com.bank.diego.dto.ResponseDto;
import com.bank.diego.exception.ClienteException;
import com.bank.diego.exception.CuentaException;
import com.bank.diego.exception.MovimientoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class ControllerException {
    private static final String MESSAGE_ERROR = "Hubo un error,por favor contacte al administrador";
    private static final Map<String, Integer> RESPONSE_STATUS_HTTP = new HashMap<>();

    public ControllerException() {
        RESPONSE_STATUS_HTTP.put(MethodArgumentNotValidException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        RESPONSE_STATUS_HTTP.put(ClienteException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        RESPONSE_STATUS_HTTP.put(CuentaException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        RESPONSE_STATUS_HTTP.put(MovimientoException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ResponseDto> mostrarExcepcion(Exception exception){
        ResponseEntity<ResponseDto> result;
        String nameExeption=exception.getClass().getSimpleName();
        String message=!nameExeption.equalsIgnoreCase("MethodArgumentNotValidException")?exception.getMessage():getMessageError((MethodArgumentNotValidException) exception);
        Integer code= RESPONSE_STATUS_HTTP.get(nameExeption);
        if(code!=null){

            result=new ResponseEntity<>(ResponseDto.builder().message(message).build(),HttpStatus.valueOf(code));
        }else{

            result=new ResponseEntity<>(ResponseDto.builder().message(MESSAGE_ERROR).build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    private String getMessageError(MethodArgumentNotValidException exception){
        return exception.getAllErrors().get(0).getDefaultMessage();
    }
}
