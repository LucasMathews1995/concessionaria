package com.concessionaria.lucasconcessionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concessionaria.lucasconcessionaria.model.Concessionaria;

import java.util.Optional;


@Repository
public interface ConcessionariaRepository extends JpaRepository<Concessionaria,Long> {



    public Optional<Concessionaria> findByNome(String nome);
}
