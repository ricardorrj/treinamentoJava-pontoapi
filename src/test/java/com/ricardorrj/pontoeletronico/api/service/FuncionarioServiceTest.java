package com.ricardorrj.pontoeletronico.api.service;

import com.ricardorrj.pontoeletronico.api.domain.Funcionario;
import com.ricardorrj.pontoeletronico.api.repositories.FuncionarioRepository;
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
public class FuncionarioServiceTest {

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;


    @Before
    public void setUp(){
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(new Funcionario());

        when(funcionarioRepository.findByCpf(anyString())).thenReturn(new Funcionario());

        when(funcionarioRepository.findByEmail(anyString())).thenReturn(new Funcionario());

        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(new Funcionario()));
    }


    @Test
    public void createTest(){
        Funcionario funcionario = this.funcionarioService.create(new Funcionario());

        assertNotNull(funcionario);
    }


    @Test
    public void findByCpfTest(){
        Optional<Funcionario> funcionario = this.funcionarioService.findByCpf("78688251460");
        assertTrue(funcionario.isPresent());
    }


    @Test
    public void findByEmailTest(){
        Optional<Funcionario> funcionario = this.funcionarioService.findByEmail("funcionario@funcionario.com.br");
        assertTrue(funcionario.isPresent());
    }


    @Test
    public void findByIdTest(){
        Optional<Funcionario> funcionario = Optional.ofNullable(this.funcionarioService.findById(1L));
        assertTrue(funcionario.isPresent());
    }


    @Test
    public void findAllTest(){
        Pageable pageable = PageRequest.of(0, 5);
        when(funcionarioRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(new Funcionario())));

        Page<Funcionario> listObjects = funcionarioService.findAll(pageable);
        assertEquals(1, listObjects.getTotalElements());
    }

    @After
    public void delete(){
        this.funcionarioService.delete(1L);
    }
}
