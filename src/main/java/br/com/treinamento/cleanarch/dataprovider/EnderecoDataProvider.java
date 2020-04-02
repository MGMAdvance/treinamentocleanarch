package br.com.treinamento.cleanarch.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinamento.cleanarch.dataprovider.repository.EnderecoRepository;

@Component
public class EnderecoDataProvider {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    // public EnderecoEntity cadastrarEndereco(EnderecoEntity entity){
    //     return null;
    // }

    // public EnderecoEntity buscarEndereco(Integer cep){
    //     return null;
    // }
}