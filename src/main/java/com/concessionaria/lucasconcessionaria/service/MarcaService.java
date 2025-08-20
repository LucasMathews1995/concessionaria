package com.concessionaria.lucasconcessionaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concessionaria.lucasconcessionaria.exceptions.MarcaExistenteException;
import com.concessionaria.lucasconcessionaria.model.Marca;
import com.concessionaria.lucasconcessionaria.repository.MarcaRepository;

@Service
public class MarcaService {


@Autowired
    private MarcaRepository repository;



    public Marca  salvarMarca(Marca marca){
   Optional<Marca> marcaExistente =  repository.findByNome(marca.getNome());
if(marcaExistente.isPresent()){
    throw new MarcaExistenteException("Marca já existente");
}
else {
    repository.save(marca);
    return marca;
}

        
    }
}
