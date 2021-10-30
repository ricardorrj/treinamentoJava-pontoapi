package com.ricardorrj.pontoeletronico.api.repositories;

import com.ricardorrj.pontoeletronico.api.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface FuncionarioRepository extends JpaRepository <Funcionario, Long> {

    Funcionario findByCpf(String cpf);

    Funcionario findByEmail(String email);

    Funcionario findByCpfOrEmail(String cpf, String email);
}
