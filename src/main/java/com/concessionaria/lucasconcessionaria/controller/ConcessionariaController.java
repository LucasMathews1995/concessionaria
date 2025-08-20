package com.concessionaria.lucasconcessionaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
