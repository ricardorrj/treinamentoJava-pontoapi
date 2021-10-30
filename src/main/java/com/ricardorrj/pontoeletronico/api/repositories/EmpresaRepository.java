package com.ricardorrj.pontoeletronico.api.repositories;

import com.ricardorrj.pontoeletronico.api.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EmpresaRepository extends JpaRepository <Empresa, Long> {

    Empresa findByCnpj(String cnpj);
    //Passando o campo direto, o Spring gera a query automaticamente
}
