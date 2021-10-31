package com.ricardorrj.pontoeletronico.api.service;

import com.ricardorrj.pontoeletronico.api.domain.Lancamento;
import com.ricardorrj.pontoeletronico.api.repositories.LancamentoRepository;
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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {

    @MockBean
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;


    @Before
    public void setUp(){
        when(lancamentoRepository.save(any(Lancamento.class))).thenReturn(new Lancamento());

        when(lancamentoRepository.findById(anyLong())).thenReturn(Optional.of(new Lancamento()));
    }


    @Test
    public void createTest(){
        Lancamento lancamento = this.lancamentoService.create(new Lancamento());

        assertNotNull(lancamento);
    }


    @Test
    public void findByIdTest(){
        Optional<Lancamento> lancamento = Optional.ofNullable(this.lancamentoService.findById(1L));

        assertTrue(lancamento.isPresent());
    }


    @Test
    public void findByFuncionarioIdTest(){
        Pageable pageable = PageRequest.of(0, 5);
        when(lancamentoRepository.findByFuncionarioId(1L, pageable)).thenReturn(new PageImpl<>(Collections.singletonList(new Lancamento())));

        Page<Lancamento> listObjects = lancamentoService.findByFuncionarioId(1L, pageable);
        assertEquals(1, listObjects.getTotalElements());
    }


    @After
    public void delete(){
        this.lancamentoService.delete(1L);
    }
}
