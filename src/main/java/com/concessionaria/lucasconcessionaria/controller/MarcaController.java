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

    @GetMapping
    public ResponseEntity<List<Marca>> listarMarcas(){
        List<Marca> marcas = service.listarMarcas();

        return ResponseEntity.ok().body(marcas);
    }



    @GetMapping("{id}")
    public ResponseEntity<Marca> recuperarMarca(@PathVariable long id){


        try{
            Marca m = service.recuperarMarca(id);
            return ResponseEntity.ok().body(m);
        }catch(MarcaExistenteException e ){
            return ResponseEntity.notFound().build();
        }


    }
@DeleteMapping("{id}")
public ResponseEntity<String> deletarMarca(@PathVariable long id){

    try{

        String message = service.deletarMarca(id);
   return     ResponseEntity.ok().body(message);

    }catch(MarcaExistenteException e ){
        return ResponseEntity.notFound().build();
    }
}

@PutMapping("{id}")
public ResponseEntity<String> atualizarMarca(@PathVariable long id, @RequestBody Marca marca){


    try {
        Marca m = service.atualizar(id, marca);
        String message = String.format("A marca %s está atualizada", m.getNome());
        return ResponseEntity.ok().body(message);

    }catch(MarcaExistenteException e ){

       return ResponseEntity.notFound().build();
    }
}




    

}
