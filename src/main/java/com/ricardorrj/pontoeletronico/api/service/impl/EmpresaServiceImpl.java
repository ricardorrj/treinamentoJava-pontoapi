package com.ricardorrj.pontoeletronico.api.service.impl;

import com.ricardorrj.pontoeletronico.api.domain.Empresa;
import com.ricardorrj.pontoeletronico.api.repositories.EmpresaRepository;
import com.ricardorrj.pontoeletronico.api.service.EmpresaService;
import com.ricardorrj.pontoeletronico.api.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;


    @Override
    public Page<Empresa> findAll(Pageable pageable){
        return this.empresaRepository.findAll(pageable);
    }


    @Override
    public Empresa findById(Long id){
        Optional<Empresa> empresa = this.empresaRepository.findById(id);
        return empresa.orElseThrow(() -> new ObjectNotFoundException("Cadastro não encontrado!"));
    }


    @Override
    public Optional<Empresa> findByCnpj(String cnpj){
        return Optional.ofNullable(this.empresaRepository.findByCnpj(cnpj));
    }


    @Override
    public Empresa create(Empresa empresa){
        empresa.setId(null);
        return this.empresaRepository.save(empresa);
    }


    @Override
    public void delete(Long id){
        this.findById(id);

        try{
            this.empresaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new com.ricardorrj.pontoeletronico.api.service.exception.DataIntegrityViolationException("Registro com informações vinculadas," +
                                                                                                            "Não pode ser deletado!");
        }
    }
}
