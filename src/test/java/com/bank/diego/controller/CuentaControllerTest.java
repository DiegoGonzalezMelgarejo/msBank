package com.bank.diego.controller;

import com.bank.diego.dto.cuenta.CreateCuentaDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc()
@SpringBootTest
public class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void save() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/cuentas")
                        .content(objectMapper.writeValueAsString(getCreateCuentaDto()))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();

        int message=mvcResult.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.CREATED.value(),message);
    }
    @Test
    public void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()0).andReturn();

        int message=mvcResult.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(),message);
    }
    @Test
    private CreateCuentaDto getCreateCuentaDto(){
        CreateCuentaDto createCuentaDto = new CreateCuentaDto();
        createCuentaDto.setClienteId(10l);
        createCuentaDto.setNumeroCuenta(33l);
        createCuentaDto.setTipoCuenta("Corriente");
        createCuentaDto.setEstado(true);
        createCuentaDto.setSaldoInicial(1000l);
        return createCuentaDto;
    }
}
