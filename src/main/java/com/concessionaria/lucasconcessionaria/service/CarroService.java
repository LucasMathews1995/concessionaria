package com.concessionaria.lucasconcessionaria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.concessionaria.lucasconcessionaria.DTO.CarroAtualizarDTO;
import com.concessionaria.lucasconcessionaria.DTO.CarroDTO;
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

    public Carro salvarCarro(CarroDTO carro) {
        Marca marca = marcaRepository.findByNome(carro.marcaNome())
                .orElseThrow(() -> new MarcaExistenteException("não há marca existente para isso"));

        if (marca != null) {
            Carro carroSalvo = new Carro(carro.nome(), carro.modelo(), carro.ano(), carro.preco(), carro.desconto(),
                    carro.cor(), marca, null);
            repository.save(carroSalvo);
            return carroSalvo;
        } else {
            return null;
        }

    }

    public List<Carro> listagemCarros() {

        List<Carro> carros = repository.findAll();

        return carros;

    }

    @Transactional
    public String atualizarCarro(CarroAtualizarDTO carroAtualizar) {
        List<Carro> carros = repository.findByNomeAndAno(carroAtualizar.nome(), carroAtualizar.ano());

        Carro c = carros.stream().findFirst().orElseThrow(() -> new CarroNotFoundException("nenhum carro encontrado"));

        c.setNome(carroAtualizar.nome());
        c.setPreco(carroAtualizar.preco());
        c.setDesconto(carroAtualizar.desconto());
        c.setCor(carroAtualizar.cor());

        repository.save(c);

        return String.format("carro atualizado com nome de : %s  , preço: %.2f e ano %d", c.getNome(), c.getPreco(),
                c.getAno());

    }

    @Transactional
    public String deletarCarro(long id) {

        Carro carro = (repository.findById(id).orElseThrow(() -> new CarroNotFoundException("carro nao encontrado")));
        Carro carro2 = carro;
        repository.deleteById(id);

        return String.format("carro %s  excluído com sucesso", carro2.getNome());

    }

    public Carro detalharCarro(long id) {

        Carro carro = (repository.findById(id).orElseThrow(() -> new CarroNotFoundException("carro nao encontrado")));
        if (carro != null) {
            return carro;
        }
        return null;

    }

    public List<Carro> listarPorNomeOrMarcaOrAno(String marca, String nome, Integer ano) {

        List<Carro> carro = repository.findByNomeAndAno(nome, ano);
        List<Carro> carrosBuscados = new ArrayList<>();
        if (ano != null) {
            carrosBuscados = carro.stream().filter(x -> x.getMarca().getNome().equals(marca))
                    .collect(Collectors.toList());
        } else {
        }

        if (!carrosBuscados.isEmpty()) {
            return carrosBuscados;

        } else {
            throw new CarroNotFoundException("nenhum carro encontrado");
        }
    }

    @Transactional
    public Carro venderCarro(CarroDTO carro) {
        List<Carro> carro2 = repository.findByNome(carro.nome());
        List<Concessionaria> concessionaria = concessionariaRepository.findAllByNome(carro.concessionariaNome())
                .orElseThrow(() -> new ConcessionariaNotFoundException("nenhuma concessionaria achada"));

        Concessionaria concessionariaAchada = concessionaria.stream().findFirst().get();

        if (concessionariaAchada != null) {
            Carro carroAchado = carro2.stream().filter(x -> x.getCor().equals(carro.cor()) && x.getAno() == carro.ano())
                    .findFirst().get();
            concessionariaAchada.setSaldo(concessionariaAchada.getSaldo() + carroAchado.getPreco()
                    - ((carroAchado.getPreco() * carroAchado.getDesconto()) / 100));

            concessionariaAchada.removerCarros(carroAchado);

            concessionariaRepository.save(concessionariaAchada);
            repository.delete(carroAchado);

            return carroAchado;
        } else {

            return null;
        }

    }

    public Carro comprarCarro(CarroDTO carroDTO) {
        Concessionaria concessionaria = concessionariaRepository.findAllByNome(carroDTO.concessionariaNome())
                .orElseThrow(() -> new CarroNotFoundException("nao há concessionaria com esse nome")).getFirst();

        Carro carro;
        if (carroDTO.ano() == null) {
            carro = repository.findByNomeAndCor(carroDTO.nome(), carroDTO.cor()).getFirst();
        } else if (carroDTO.cor() == null) {

            carro = repository.findByNomeAndAno(carroDTO.nome(), carroDTO.ano()).getFirst();
        } else {
            carro = repository.findByNomeAndAnoAndCor(carroDTO.nome(), carroDTO.ano(), carroDTO.cor()).getFirst();
        }

        concessionaria.setSaldo(concessionaria.getSaldo() - carro.getPreco()
                - ((carro.getPreco() * carro.getDesconto()) / 100));

                
        concessionaria.addCarros(carro);
        concessionariaRepository.save(concessionaria);
        return carro;

    }

}
