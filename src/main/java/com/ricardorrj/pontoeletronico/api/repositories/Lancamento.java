package com.ricardorrj.pontoeletronico.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Lancamento extends JpaRepository <Lancamento, Long> {
    
}
