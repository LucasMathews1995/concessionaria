package com.concessionaria.lucasconcessionaria.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.concessionaria.lucasconcessionaria.exceptions.ConcessionariaNotFoundException;
import com.concessionaria.lucasconcessionaria.model.Concessionaria;
import com.concessionaria.lucasconcessionaria.service.ConcessionariaService;

@RestController
@RequestMapping("/concessionaria")
public class ConcessionariaController {


@Autowired
private ConcessionariaService service;



    @PostMapping("/salvar")
    public ResponseEntity<Concessionaria> salvarConcessionaria(@RequestBody Concessionaria concessionaria){


        try{

          Concessionaria c =   service.salvarConcessionaria(concessionaria);
        return  ResponseEntity.ok().body(c);
        }catch(ConcessionariaNotFoundException e ){
            throw new ConcessionariaNotFoundException("Concessionaria nao existente");
        }
    }


    @GetMapping("/buscarNomes")
    public ResponseEntity<List<Concessionaria>> acharConcessionariaPorNome(@RequestParam(required = true) String nome){
   
        
             List<Concessionaria> c = service.acharConcessionaria(nome);
             if(!c.isEmpty()){
             return ResponseEntity.ok().body(c);}else {return ResponseEntity.notFound().build();}
        
            
        

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletarConcessionaria(@PathVariable long id){
       try {
            String message = service.deletarConcessionaria(id);
            return ResponseEntity.ok().body(message);
       } catch (ConcessionariaNotFoundException e) {
        return ResponseEntity.notFound().build();
       }     
    }



    @PutMapping("{id}")
    public ResponseEntity<Concessionaria> atualizarConcessionaria (@PathVariable long id,@RequestParam(required = false) String nome, @RequestParam (required = false)String cidade,@RequestParam(required = false) String estado ){
      

        
try {

      Concessionaria concessionaria  = service.atualizarConcessionaria(id,nome, cidade, estado);
            return ResponseEntity.ok().body(concessionaria);
       } catch (ConcessionariaNotFoundException e) {
        return ResponseEntity.notFound().build();
       }   

    }

    @GetMapping()
    public ResponseEntity<List<Concessionaria>>  listarConcessionarias(){


            List<Concessionaria> concessionarias = service.listaConcessionarias();
            return ResponseEntity.ok().body(concessionarias);
        
    }


    


}
