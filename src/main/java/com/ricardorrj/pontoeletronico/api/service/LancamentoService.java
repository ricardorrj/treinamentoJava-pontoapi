package com.ricardorrj.pontoeletronico.api.service;

import com.ricardorrj.pontoeletronico.api.domain.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoService {

    Page<Lancamento> findByFuncionarioId(Long funcionarioId, Pageable pageable);

    Lancamento findById(Long id);

    Lancamento create(Lancamento lancamento);

    void delete(Long id);
}
