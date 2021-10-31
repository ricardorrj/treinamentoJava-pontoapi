package com.ricardorrj.pontoeletronico.api.service.impl;

import com.ricardorrj.pontoeletronico.api.domain.Funcionario;
import com.ricardorrj.pontoeletronico.api.repositories.FuncionarioRepository;
import com.ricardorrj.pontoeletronico.api.service.FuncionarioService;
import com.ricardorrj.pontoeletronico.api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @Override
    public Page<Funcionario> findAll(Pageable pageable){
        return this.funcionarioRepository.findAll(pageable);
    }


    @Override
    public Funcionario create(Funcionario funcionario){
        return this.funcionarioRepository.save(funcionario);
    }


    @Override
    public Optional<Funcionario> findByCpf(String cpf){
        return Optional.ofNullable(this.funcionarioRepository.findByCpf(cpf));
    }


    @Override
    public Optional<Funcionario> findByEmail(String email){
        return Optional.ofNullable(this.funcionarioRepository.findByEmail(email));
    }


    @Override
    public Funcionario findById(Long id){
        Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
        return funcionario.orElseThrow(() -> new ObjectNotFoundException("Cadastro não encontrado!"));
    }


    @Override
    public void delete(Long id){
        this.findById(id);

        try{
            this.funcionarioRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new com.ricardorrj.pontoeletronico.api.service.exception.DataIntegrityViolationException("Registro com informações vinculadas," +
                                                                                                            "Não pode ser deletado!");
        }
    }

}
