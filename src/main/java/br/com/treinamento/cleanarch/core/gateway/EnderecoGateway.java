package br.com.treinamento.cleanarch.core.gateway;

import java.util.List;

import br.com.treinamento.cleanarch.core.entity.EnderecoEntity;

public interface EnderecoGateway {
    EnderecoEntity cadastrarEndereco(EnderecoEntity entity);

    List<EnderecoEntity> buscarEndereco(Integer cep);
}