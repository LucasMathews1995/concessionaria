package com.concessionaria.lucasconcessionaria.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "tb_carros")
public class Carro {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long id;


    private String nome; 
     private Modelo modelo; 
    private int ano; 
    private double preco;
    private double desconto;
    private String cor;

     @ManyToOne
    @JoinColumn(name = "marca_id")
    @JsonBackReference
    private Marca marca;
    @ManyToOne
    @JoinColumn(name = "concessionaria_id")
    @JsonBackReference
    private Concessionaria concessionaria;





    
    public Carro() {
    }



    public Carro(String nome, Modelo modelo, int ano, Double preco, double desconto,String cor,Marca marca,Concessionaria concessionaria) {
        this.nome = nome;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.desconto = desconto;
        this.marca = marca;
        this.concessionaria= concessionaria;
    }


    
    public void setPreco(double preco) {
        this.preco = preco;
    }



    public String getCor() {
        return cor;
    }



    public void setCor(String cor) {
        this.cor = cor;
    }



    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Modelo getModelo() {
        return modelo;
    }
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public double getDesconto() {
        return desconto;
    }
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }



    public Marca getMarca() {
        return marca;
    }



    public void setMarca(Marca marca) {
        this.marca = marca;
    }



    public Concessionaria getConcessionaria() {
        return concessionaria;
    }



    public void setConcessionaria(Concessionaria concessionaria) {
        this.concessionaria = concessionaria;
    }


    



}
