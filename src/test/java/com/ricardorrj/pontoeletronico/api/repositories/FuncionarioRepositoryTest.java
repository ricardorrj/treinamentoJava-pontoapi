package com.ricardorrj.pontoeletronico.api.repositories;

import com.ricardorrj.pontoeletronico.api.domain.Empresa;
import com.ricardorrj.pontoeletronico.api.domain.Funcionario;
import com.ricardorrj.pontoeletronico.api.domain.enums.PerfilEnum;
import com.ricardorrj.pontoeletronico.api.utils.PasswordUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;


    private static final String CPF = "78688251460";
    private static final String EMAIL = "funcionario@funcionario.com.br";


    @Before
    public void cenario() throws Exception{
        Empresa empresa = this.empresaRepository.save(dadosEmpresa());
        this.funcionarioRepository.save(dadosFuncionario(empresa));
    }


    @Test
    public void findByCpfTest(){
        Funcionario funcionario = this.funcionarioRepository.findByCpf(CPF);

        assertEquals(CPF, funcionario.getCpf());
    }


    @Test
    public void findByEmailTest(){
        Funcionario funcionario = this.funcionarioRepository.findByEmail(EMAIL);

        assertEquals(EMAIL, funcionario.getEmail());
    }


    @Test
    public void findByCpfOrEmail_cpfInvalidoTest(){
        Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail("00055511133", EMAIL);

        assertNotNull(funcionario);
    }


    @Test
    public void findByCpfOrEmail_emailInvalidoTest(){
        Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, "emailinvalido@emailinvalido.com.br");

        assertNotNull(funcionario);
    }


    @Test
    public void findByCpfOrEmailTest(){
        Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);

        assertNotNull(funcionario);
    }


    @After
    public void delete(){
        this.funcionarioRepository.deleteAll();
        this.empresaRepository.deleteAll();
    }


    private Empresa dadosEmpresa(){
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("Cadastro Exemplo!");
        empresa.setCnpj("04275226000156");

        return empresa;
    }

    private Funcionario dadosFuncionario(Empresa dadosEmpresa){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionário");
        funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
        funcionario.setSenha(PasswordUtils.crypt("Senha_super_forte"));
        funcionario.setCpf(CPF);
        funcionario.setEmail(EMAIL);
        funcionario.setHorasTrabalhadas(8.0F);
        funcionario.setHorasAlmoco(1.0F);
        funcionario.setValorHora(new BigDecimal("27.50"));
        funcionario.setEmpresa(dadosEmpresa);
        return funcionario;
    }

}
