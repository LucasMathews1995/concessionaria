package com.concessionaria.lucasconcessionaria.DTO;

import com.concessionaria.lucasconcessionaria.model.Modelo;

public record CarroDTO(String nome,Modelo modelo, int ano , double preco,double desconto,String cor,String marcaNome,String concessionariaNome) {

}
