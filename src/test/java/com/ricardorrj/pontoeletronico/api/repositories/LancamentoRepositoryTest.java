package com.ricardorrj.pontoeletronico.api.repositories;

import com.ricardorrj.pontoeletronico.api.domain.Empresa;
import com.ricardorrj.pontoeletronico.api.domain.Funcionario;
import com.ricardorrj.pontoeletronico.api.domain.Lancamento;
import com.ricardorrj.pontoeletronico.api.domain.enums.PerfilEnum;
import com.ricardorrj.pontoeletronico.api.domain.enums.TipoEnum;
import com.ricardorrj.pontoeletronico.api.utils.PasswordUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;


    private Long idFuncionario;
    private static final String CPF = "78688251460";
    private static final String EMAIL = "funcionario@funcionario.com.br";


    @Before
    public void cenario() throws Exception {
        Empresa empresa = this.empresaRepository.save(dadosEmpresa());
        Funcionario funcionario = this.funcionarioRepository.save(dadosFuncionario(empresa));
        this.idFuncionario = funcionario.getId();

        this.lancamentoRepository.save(dadosLancamentos(funcionario));
        this.lancamentoRepository.save(dadosLancamentos(funcionario));
    }



    @Test
    public void findByFuncionarioIdTest(){
        List<Lancamento> listObjects = this.lancamentoRepository.findByFuncionarioId(this.idFuncionario);

        assertEquals(2, listObjects.size());
    }

    @Test
    public void findByFuncionarioIdPaginada_test(){
        Pageable pageable = PageRequest.of(0,5);
        Page<Lancamento> listObjects = this.lancamentoRepository.findByFuncionarioId(this.idFuncionario, pageable);

        assertEquals(2, listObjects.getTotalElements());
    }



    @After
    public void delete(){
        this.lancamentoRepository.deleteAll();
        this.funcionarioRepository.deleteAll();
        this.empresaRepository.deleteAll();
    }


    private Lancamento dadosLancamentos(Funcionario funcionario) {
        Lancamento lancameto = new Lancamento();
        lancameto.setData(new Date());
        lancameto.setDescricao("Registro teste");
        lancameto.setLocalizacao("Local de trabalho");
        lancameto.setTipo(TipoEnum.INICIO_ALMOCO);
        lancameto.setFuncionario(funcionario);
        return lancameto;
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
