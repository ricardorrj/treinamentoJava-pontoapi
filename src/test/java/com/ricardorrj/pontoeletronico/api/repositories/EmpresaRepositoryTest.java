package com.ricardorrj.pontoeletronico.api.repositories;

import com.ricardorrj.pontoeletronico.api.domain.Empresa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaRepositoryTest {

    @Autowired
    private EmpresaRepository empresaRepository;


    @Before
    public void save() throws Exception{
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("Cadastro Exemplo!");
        empresa.setCnpj("04275226000156");

        this.empresaRepository.save(empresa);
    }


    @Test
    public void testBuscaPorCnpj(){
        Empresa empresa = this.empresaRepository.findByCnpj("04275226000156");
        assertEquals("04275226000156", empresa.getCnpj());
    }


    @Test
    public void testfindById(){
        Empresa cenario = this.empresaRepository.findByCnpj("04275226000156");

        Optional<Empresa> empresa = this.empresaRepository.findById(cenario.getId());
        assertEquals(cenario.getId(), empresa.get().getId());
    }


    @After
    public void delete(){
        this.empresaRepository.deleteAll();
    }


    //@Before -> Executa sempre antes do @Test
    //@After  -> Executa depois do @Test

}
