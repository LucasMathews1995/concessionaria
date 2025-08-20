package com.concessionaria.lucasconcessionaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.concessionaria.lucasconcessionaria.DTO.CarroDTO;
import com.concessionaria.lucasconcessionaria.exceptions.CarroNotFoundException;
import com.concessionaria.lucasconcessionaria.service.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {

@Autowired
private CarroService service;

@PostMapping("/salvar")
    public ResponseEntity<CarroDTO> salvarCarro(@RequestBody CarroDTO carro){
        
        try{

            service.salvarCarro(carro);

       return     ResponseEntity.ok().body(carro);

        }catch(CarroNotFoundException e ){
            throw new CarroNotFoundException("O carro não foi encontrado, tente outro");
           
        }

    }

}
