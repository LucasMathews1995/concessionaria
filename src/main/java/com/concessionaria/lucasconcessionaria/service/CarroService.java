package com.concessionaria.lucasconcessionaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concessionaria.lucasconcessionaria.DTO.CarroDTO;
import com.concessionaria.lucasconcessionaria.model.Carro;
import com.concessionaria.lucasconcessionaria.model.Concessionaria;
import com.concessionaria.lucasconcessionaria.model.Marca;
import com.concessionaria.lucasconcessionaria.repository.CarroRepository;
import com.concessionaria.lucasconcessionaria.repository.ConcessionariaRepository;
import com.concessionaria.lucasconcessionaria.repository.MarcaRepository;

@Service
public class CarroService {


    @Autowired
    private CarroRepository repository;

@Autowired 
private ConcessionariaRepository concessionariaRepository;

@Autowired 
private MarcaRepository marcaRepository;




    public Carro salvarCarro(CarroDTO carro){
Optional<Concessionaria> concessionariaExistente=  concessionariaRepository.findByNome(carro.concessionariaNome());
   Optional<Marca> marcaExistente=  marcaRepository.findByNome(carro.marcaNome());


        if(marcaExistente.isPresent() && concessionariaExistente.isPresent()){
        Carro carroSalvo = new Carro(carro.nome(), carro.modelo(), carro.ano(), carro.preco(),carro.desconto(), marcaExistente.get(), concessionariaExistente.get());
        repository.save(carroSalvo);
        return carroSalvo;
    }else {
        return null;
    }
}

}
