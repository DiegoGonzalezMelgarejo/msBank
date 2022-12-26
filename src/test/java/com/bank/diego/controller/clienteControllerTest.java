package com.bank.diego.controller;

import com.bank.diego.dto.cliente.CreateClienteDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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
public class clienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void save() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/clientes")
                        .content(objectMapper.writeValueAsString(getCreateClienteDto()))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();

        int message=mvcResult.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.CREATED.value(),message);
    }
    @Test
    public void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        int message=mvcResult.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(),message);
    }
    @Test
    public void findBYiD() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/clientes")
                        .param("id","1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        int message=mvcResult.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(),message);
    }

    private CreateClienteDto getCreateClienteDto(){
        CreateClienteDto createClienteDto=new CreateClienteDto();
        createClienteDto.setClave("sdfsdf");
        createClienteDto.setGenero("sdfsdf");
        createClienteDto.setEdad(25);
        createClienteDto.setTelefono("sdfsdf");
        createClienteDto.setDireccion("sfsdf");
        createClienteDto.setEstado(true);
        createClienteDto.setNombre("cliente prueba ");
        createClienteDto.setIdentificacion("asdasd");
        return  createClienteDto;
    }
}
