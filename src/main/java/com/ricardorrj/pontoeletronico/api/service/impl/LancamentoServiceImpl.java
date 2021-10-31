package com.ricardorrj.pontoeletronico.api.service.impl;

import com.ricardorrj.pontoeletronico.api.domain.Lancamento;
import com.ricardorrj.pontoeletronico.api.repositories.LancamentoRepository;
import com.ricardorrj.pontoeletronico.api.service.LancamentoService;
import com.ricardorrj.pontoeletronico.api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;


    @Override
    public Lancamento create(Lancamento lancamento){
        return this.lancamentoRepository.save(lancamento);
    }


    @Override
    public Page<Lancamento> findByFuncionarioId(Long funcionarioId,
                                                Pageable pageable) {
        return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageable);
    }


    @Override
    public Lancamento findById(Long id){
        Optional<Lancamento> funcionario = this.lancamentoRepository.findById(id);
        return funcionario.orElseThrow(() -> new ObjectNotFoundException("Cadastro não encontrado!"));
    }


    @Override
    public void delete(Long id){
        this.findById(id);

        try{
            this.lancamentoRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new com.ricardorrj.pontoeletronico.api.service.exception.DataIntegrityViolationException("Registro com informações vinculadas," +
                                                                                                            "Não pode ser deletado!");
        }
    }
}
