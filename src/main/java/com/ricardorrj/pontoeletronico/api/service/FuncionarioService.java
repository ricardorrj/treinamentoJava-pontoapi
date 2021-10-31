package com.ricardorrj.pontoeletronico.api.service;

import com.ricardorrj.pontoeletronico.api.domain.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FuncionarioService {


    Funcionario create(Funcionario funcionario);

    Optional<Funcionario> findByCpf(String cpf);


    Optional<Funcionario> findByEmail(String email);


    Funcionario findById(Long id);


    Page<Funcionario> findAll(Pageable pageable);


    void delete(Long id);
}
