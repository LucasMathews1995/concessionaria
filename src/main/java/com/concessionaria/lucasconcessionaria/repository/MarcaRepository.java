package com.concessionaria.lucasconcessionaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.concessionaria.lucasconcessionaria.model.Marca;
@Repository
public interface MarcaRepository  extends JpaRepository<Marca,Long>{



    public Optional<Marca> findByNome(String nome);
    
}
