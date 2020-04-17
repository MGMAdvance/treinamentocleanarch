package br.com.treinamento.cleanarch.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinamento.cleanarch.configuration.exception.DataBaseException;
import br.com.treinamento.cleanarch.core.entity.ClienteEntity;
import br.com.treinamento.cleanarch.core.gateway.ClienteGateway;
import br.com.treinamento.cleanarch.dataprovider.entity.ClienteTable;
import br.com.treinamento.cleanarch.dataprovider.mapper.ClienteTableMapper;
import br.com.treinamento.cleanarch.dataprovider.repository.ClienteRepository;

@Component
public class ClienteDataProvider implements ClienteGateway{
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteEntity cadastrarCliente(ClienteEntity entity){
        try{
            ClienteTable table = ClienteTableMapper.to(entity);

            table = clienteRepository.save(table);

            entity = ClienteTableMapper.from(table);

            return entity;
        }catch(Exception e){
            throw new DataBaseException("[E01] Falha no cadastro do cliente!");
        }

    }

}