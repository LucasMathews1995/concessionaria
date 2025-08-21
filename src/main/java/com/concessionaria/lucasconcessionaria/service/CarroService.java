package com.concessionaria.lucasconcessionaria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concessionaria.lucasconcessionaria.DTO.CarroAtualizarDTO;
import com.concessionaria.lucasconcessionaria.DTO.CarroDTO;
import com.concessionaria.lucasconcessionaria.DTO.CarroGetDTO;
import com.concessionaria.lucasconcessionaria.exceptions.CarroNotFoundException;
import com.concessionaria.lucasconcessionaria.exceptions.ConcessionariaNotFoundException;
import com.concessionaria.lucasconcessionaria.exceptions.MarcaExistenteException;
import com.concessionaria.lucasconcessionaria.model.Carro;
import com.concessionaria.lucasconcessionaria.model.Concessionaria;
import com.concessionaria.lucasconcessionaria.model.Marca;
import com.concessionaria.lucasconcessionaria.repository.CarroRepository;
import com.concessionaria.lucasconcessionaria.repository.ConcessionariaRepository;
import com.concessionaria.lucasconcessionaria.repository.MarcaRepository;

import jakarta.transaction.Transactional;

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
        Carro carroSalvo = new Carro(carro.nome(), carro.modelo(), carro.ano(), carro.preco(),carro.desconto(),carro.cor(), marcaExistente.get(), concessionariaExistente.get());
        repository.save(carroSalvo);
        return carroSalvo;
    }else {
        return null;
    }
}


public List<Carro> listagemCarros(){


    List<Carro> carros= repository.findAll();
   
    return carros;


}

@Transactional
public String atualizarCarro(CarroAtualizarDTO carroAtualizar){
       List<Carro> carros = repository.findByNomeAndAno(carroAtualizar.nome(),carroAtualizar.ano());

       
   Carro c = carros.stream().findFirst().orElseThrow(()-> new CarroNotFoundException("nenhum carro encontrado"));

        c.setNome(carroAtualizar.nome());
        c.setPreco(carroAtualizar.preco());    
        c.setDesconto(carroAtualizar.desconto());
        c.setCor(carroAtualizar.cor());
       
        repository.save(c);
         
        
        
        return String.format("carro atualizado com nome de : %s  , preço: %.2f e ano %d", c.getNome(), c.getPreco(),c.getAno());



}

@Transactional
public String deletarCarro(long id ){

    Carro carro  =(repository.findById(id).orElseThrow(()-> new CarroNotFoundException("carro nao encontrado")));
    Carro carro2 =carro;
    repository.deleteById(id);

    
    return String.format("carro %s  excluído com sucesso", carro2.getNome());

}

public Carro detalharCarro (long id ){

     Carro carro  =(repository.findById(id).orElseThrow(()-> new CarroNotFoundException("carro nao encontrado")));
        if(carro!=null){ return carro;}
        return null;
    

}

public List<Carro> listarPorNomeAndMarcaAndAno (String marca, String nome , int ano){

List<Carro> carro  = repository.findByNomeOrAno(nome, ano);

   List<Carro> carrosBuscados = carro.stream().filter(x-> x.getMarca().getNome().equals(marca)).collect(Collectors.toList());
    
if(!carrosBuscados.isEmpty()){
return carrosBuscados;
    
}else
{
    throw new CarroNotFoundException("nenhum carro encontrado");
}






}

}
