package com.concessionaria.lucasconcessionaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.concessionaria.lucasconcessionaria.exceptions.MarcaExistenteException;
import com.concessionaria.lucasconcessionaria.model.Marca;
import com.concessionaria.lucasconcessionaria.service.MarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaService service;



@PostMapping("/salvar")
    public ResponseEntity<Marca> salvarMarca(@RequestBody Marca marca){
      
        try{
            Marca marcasalva =  service.salvarMarca(marca); 
            return ResponseEntity.ok().body(marcasalva);
        }catch(MarcaExistenteException e ){
            throw new MarcaExistenteException("marca existente");
        }


    }

    

}
