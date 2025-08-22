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

@Entity
@Table(name = "tb_concessionaria")
public class Concessionaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 
     private String nome;
    private String cidade;
    private String estado;
    private Double saldo;

    @OneToMany(mappedBy= "concessionaria",cascade = CascadeType.ALL,orphanRemoval = true) 
    @JsonManagedReference
    private List<Carro> carros;




    
    public Concessionaria() {
    }






    public Concessionaria(String nome, String cidade, String estado, Double saldo) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.saldo = saldo;
        this.carros = new ArrayList<>();
    }






    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }






    public List<Carro> getCarros() {
        return carros;
    }






    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    public void addCarros (Carro carro){
        
        this.carros.add(carro);
        carro.setConcessionaria(this);
        
        
    }
public void removerCarros(Carro carro){
    this.carros.remove(carro);

    carro.setConcessionaria(null);
}






@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    return result;
}






@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Concessionaria other = (Concessionaria) obj;
    if (id != other.id)
        return false;
    if (nome == null) {
        if (other.nome != null)
            return false;
    } else if (!nome.equals(other.nome))
        return false;
    return true;
}


    

   


}
