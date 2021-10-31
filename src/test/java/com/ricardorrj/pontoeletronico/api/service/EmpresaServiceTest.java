package com.ricardorrj.pontoeletronico.api.service;

import com.ricardorrj.pontoeletronico.api.domain.Empresa;
import com.ricardorrj.pontoeletronico.api.repositories.EmpresaRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaServiceTest {

    @MockBean
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;

    private static final String CNPJ = "04275226000156";


    @Before
    public void setUp() throws Exception{
        when(empresaRepository.save(any(Empresa.class))).thenReturn(new Empresa());

        when(empresaRepository.findByCnpj(anyString())).thenReturn(new Empresa());

        when(empresaRepository.findById(anyLong())).thenReturn(Optional.of(new Empresa()));
    }


    @Test
    public void findByCnpjTest(){
        Optional<Empresa> empresa = this.empresaService.findByCnpj(CNPJ);
        assertTrue(empresa.isPresent());
    }


    @Test
    public void findByIdTest(){
        Optional<Empresa> empresa = Optional.ofNullable(this.empresaService.findById(1L));
        assertTrue(empresa.isPresent());
    }


    @Test
    public void findAllTest(){
        Pageable pageable = PageRequest.of(0, 5);
        when(empresaRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(new Empresa())));

        Page<Empresa> listObjects = empresaService.findAll(pageable);
        assertEquals(1, listObjects.getTotalElements());
    }


    @Test
    public void createTest(){
        Empresa empresa = this.empresaService.create(new Empresa());

        assertNotNull(empresa);
    }


    @After
    public void delete(){
        empresaService.delete(1L);
    }


    //@MockBean -> Objeto falso, para testes
}
