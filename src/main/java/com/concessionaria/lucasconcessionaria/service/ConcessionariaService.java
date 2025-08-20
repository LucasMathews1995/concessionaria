package com.concessionaria.lucasconcessionaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.concessionaria.lucasconcessionaria.exceptions.ConcessionariaNotFoundException;
import com.concessionaria.lucasconcessionaria.model.Concessionaria;
import com.concessionaria.lucasconcessionaria.repository.ConcessionariaRepository;

@Service
public class ConcessionariaService {



    @Autowired
    private ConcessionariaRepository repository;




    public Concessionaria salvarConcessionaria( Concessionaria concessionaria){

        Optional<Concessionaria> concessionaria2 = repository.findByNome(concessionaria.getNome());


        if(concessionaria2.isPresent()){
         throw new ConcessionariaNotFoundException("encontrada a concessionaria"+ concessionaria2.get().getNome());
        }else {
             repository.save(concessionaria);
        
        
        return concessionaria; }
       

        
    
       
        }
}
