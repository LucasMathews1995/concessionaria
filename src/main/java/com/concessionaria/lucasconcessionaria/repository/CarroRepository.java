package com.concessionaria.lucasconcessionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concessionaria.lucasconcessionaria.model.Carro;


@Repository
public interface CarroRepository extends JpaRepository<Carro,Long> {

    


}
