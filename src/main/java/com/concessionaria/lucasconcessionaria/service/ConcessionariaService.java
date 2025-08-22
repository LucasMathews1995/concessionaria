package com.concessionaria.lucasconcessionaria.service;

import java.util.List;
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


        public List<Concessionaria> acharConcessionaria( String nome){
            List<Concessionaria> concessionaria = repository.findAllByNome(nome).orElseThrow(()-> new ConcessionariaNotFoundException("concessionária não encontrada"));
                if(!concessionaria.isEmpty()){ return concessionaria;}else 
                {return null; }
    
        }
        public String deletarConcessionaria(long id){
            Concessionaria concessionaria = repository.findById(id).orElseThrow(()-> new ConcessionariaNotFoundException("nao foi encontrada nenhuma concessionaria"));
            Concessionaria c = concessionaria;
            repository.delete(concessionaria);
            return String.format("a concessionaria %s foi deletada do estado de %s", c.getNome(),c.getEstado());
           
        }


        public Concessionaria atualizarConcessionaria(long id,String nome, String cidade, String estado){

            Concessionaria concessionaria = repository.findById(id).orElseThrow(()-> new ConcessionariaNotFoundException("nenhuma concessionaria foi encontrada"));

            if(concessionaria!=null&& nome==null){
               concessionaria.setCidade(cidade);
               concessionaria.setEstado(estado);
               
        }else if(cidade!=null){
            concessionaria.setEstado(estado);
            concessionaria.setNome(nome);
          
            return concessionaria;
        }else if(estado!=null){
             concessionaria.setNome(nome);
               concessionaria.setCidade(cidade);
        }

          repository.save(concessionaria);
          return concessionaria;
    
    }

    public List<Concessionaria> listaConcessionarias (){


        List<Concessionaria> concessionarias = repository.findAll();
        if(concessionarias.isEmpty()){
return null;
        }

        return concessionarias;
    }


    
}
