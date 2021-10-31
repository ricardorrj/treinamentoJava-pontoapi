package com.ricardorrj.pontoeletronico.api.service;

import com.ricardorrj.pontoeletronico.api.domain.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmpresaService {

    Empresa findById(Long id);

    Page<Empresa> findAll(Pageable pageable);

    Optional<Empresa> findByCnpj(String cnpj);

    Empresa create(Empresa empresa);

    void delete(Long id);
}
