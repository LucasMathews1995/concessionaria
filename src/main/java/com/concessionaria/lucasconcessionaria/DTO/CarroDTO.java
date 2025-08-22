package com.concessionaria.lucasconcessionaria.DTO;

import com.concessionaria.lucasconcessionaria.model.Modelo;

public record CarroDTO(String nome,Modelo modelo, Integer ano , Double preco,Double desconto,String cor,String marcaNome,String concessionariaNome) {

}
