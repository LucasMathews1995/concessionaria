package com.concessionaria.lucasconcessionaria.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity()
@Table(name = "tb_marca")
public class Marca {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String nacionalidade;

    private String categoria;


    @OneToMany(mappedBy = "marca", orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Carro> carros;


   


    public Marca() {
    }



    public Marca (String nome, String nacionalidade, String categoria){
        this.nome= nome;
        this.nacionalidade = nacionalidade;
        this.categoria = categoria;
       
        this.carros = new ArrayList<>();

    }



    public void setId(long id) {
        this.id = id;
    }



    public void setNome(String nome) {
        this.nome = nome;
    }



    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }



    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }



    

  public String getCategoria() {
    return categoria;
  }



  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }


    public long getId() {
        return id;
    }



    public String getNome() {
        return nome;
    }



    public String getNacionalidade() {
        return nacionalidade;
    }



    public List<Carro> getCarros() {
        return carros;
    }

    public void addCarros (Carro carro){
        this.carros.add(carro);
        carro.setMarca(this);
    }
  public void removeMarca (Carro carro){
        this.carros.remove(carro);
        carro.setMarca(null);
    }




    


    
    

}
