package com.concessionaria.lucasconcessionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concessionaria.lucasconcessionaria.model.Carro;
import java.util.List;



@Repository
public interface CarroRepository extends JpaRepository<Carro,Long> {

    public List<Carro> findByNome(String nome);
    public List<Carro> findByNomeAndAno(String nome, Integer ano);
    public List<Carro> findByNomeAndCor(String nome, String cor);
    public List<Carro> findByNomeAndAnoAndCor(String nome, Integer ano , String cor);
 

}
