package com.concessionaria.lucasconcessionaria.service;

import java.util.List;
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


    public List<Marca> listarMarcas(){
        List<Marca> marcas = repository.findAll();
            if(!marcas.isEmpty()){
                return null;
            }
        return marcas;
        

    }


    public Marca recuperarMarca(long id){
        Marca m =repository.findById(id).orElseThrow(()-> new MarcaExistenteException("não há marca  com esse id"));
return m;
    }


    public String deletarMarca(long id){
        Marca m = repository.findById(id).orElseThrow(()-> new MarcaExistenteException("não há marca  com esse id"));
        repository.delete(m);
        return "marca deletada com sucesso";


    }

    public Marca atualizar(long id , Marca marca){
        Marca m = repository.findById(id).orElseThrow(()-> new MarcaExistenteException("não foi achado a marca com esse id"));

        if(marca.getCategoria().isBlank()){
        
        m.setNome(marca.getNome());
        m.setNacionalidade(marca.getNacionalidade());}
        else if(marca.getNacionalidade().isBlank()){
            m.setNome(marca.getNome());
            m.setCategoria(marca.getCategoria());
        }else {
            m.setCategoria(marca.getCategoria());
             m.setNome(marca.getNome());
        m.setNacionalidade(marca.getNacionalidade());
        }
        

        repository.save(m);


        return m;
    }

}
