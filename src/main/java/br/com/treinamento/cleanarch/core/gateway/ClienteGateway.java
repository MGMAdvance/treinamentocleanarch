package br.com.treinamento.cleanarch.core.gateway;

import br.com.treinamento.cleanarch.core.entity.ClienteEntity;

public interface ClienteGateway {
    ClienteEntity cadastrarCliente(ClienteEntity entity);
}