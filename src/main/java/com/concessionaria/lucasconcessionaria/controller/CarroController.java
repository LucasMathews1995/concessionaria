package com.concessionaria.lucasconcessionaria.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.concessionaria.lucasconcessionaria.DTO.CarroAtualizarDTO;
import com.concessionaria.lucasconcessionaria.DTO.CarroDTO;
import com.concessionaria.lucasconcessionaria.exceptions.CarroNotFoundException;
import com.concessionaria.lucasconcessionaria.model.Carro;
import com.concessionaria.lucasconcessionaria.service.CarroService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/veiculos")
public class CarroController {

    @Autowired
    private CarroService service;

    @PostMapping("/salvar")
    public ResponseEntity<CarroDTO> salvarCarro(@RequestBody CarroDTO carro) {

        try {

            service.salvarCarro(carro);

            return ResponseEntity.ok().body(carro);

        } catch (CarroNotFoundException e) {
            throw new CarroNotFoundException("O carro não foi encontrado, tente outro");

        }

    }

    @GetMapping()
    public ResponseEntity<List<Carro>> listagemCarro() {
        try {

            List<Carro> carros = service.listagemCarros();

            return ResponseEntity.ok().body(carros);
        } catch (CarroNotFoundException e) {
            throw new CarroNotFoundException("Carros não encontrado");
        }
    }

    @PutMapping()
    public ResponseEntity<String> atualizarLista(@PathVariable CarroAtualizarDTO carro) {

        String mensagem = service.atualizarCarro(carro);
        return ResponseEntity.ok().body(mensagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCarro(@PathVariable long id) {

        try {

            service.deletarCarro(id);
            return ResponseEntity.ok("Carro com ID " + id + " excluído com sucesso.");

        } catch (CarroNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> detalharCarro(@PathVariable long id) {
        try {

            Carro carro = service.detalharCarro(id);

            return ResponseEntity.ok(carro);
        } catch (CarroNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Carro>> buscarPorNomeOrMarcaOrAno(@RequestParam(required = false) String nome,
            @RequestParam(required = false) String marca, @RequestParam(required = false) Integer ano) {
        try {
            if (ano != null) {
                List<Carro> carro = service.listarPorNomeOrMarcaOrAno(marca, nome, ano);
                return ResponseEntity.ok().body(carro);
            } else {
                List<Carro> carro = service.listarPorNomeOrMarcaOrAno(marca, nome, null);
                return ResponseEntity.ok().body(carro);
            }

        } catch (CarroNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
@PostMapping("/vender")
    public ResponseEntity<Carro> venderCarro(@RequestBody CarroDTO carro){

        try {

        var carro2 =    service.venderCarro(carro);

      return  ResponseEntity.ok().body(carro2);
            
        } catch (CarroNotFoundException e) {
            return null;
        }


    }

@PostMapping("/comprar")
    public ResponseEntity<Carro> comprarCarro(@RequestBody CarroDTO carro){


        try{
           Carro c= service.comprarCarro(carro);
            return ResponseEntity.ok().body(c);

        } catch(CarroNotFoundException e ){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);


        }
    }

}
