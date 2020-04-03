package br.com.treinamento.cleanarch.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinamento.cleanarch.configuration.exception.DataBaseException;
import br.com.treinamento.cleanarch.core.entity.EnderecoEntity;
import br.com.treinamento.cleanarch.core.gateway.EnderecoGateway;
import br.com.treinamento.cleanarch.dataprovider.entity.EnderecoTable;
import br.com.treinamento.cleanarch.dataprovider.mapper.EnderecoTableMapper;
import br.com.treinamento.cleanarch.dataprovider.repository.EnderecoRepository;

@Component
public class EnderecoDataProvider implements EnderecoGateway {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoEntity cadastrarEndereco(EnderecoEntity entity){
        try{
            EnderecoTable table = EnderecoTableMapper.to(entity);

            table = enderecoRepository.save(table);
            
            entity = EnderecoTableMapper.from(table);

            return entity;
        }catch(Exception e){
            throw new DataBaseException();
        }

    }

    public List<EnderecoEntity> buscarEndereco(Integer cep){
        try{
            List<EnderecoEntity> listaEntity = new ArrayList<>();
            List<EnderecoTable> listaTable = new ArrayList<>();

            listaTable = enderecoRepository.findByCep(cep);

            for(EnderecoTable endereco : listaTable){
                listaEntity.add(EnderecoTableMapper.from(endereco));
            }

            return listaEntity;
        }catch(Exception e){
            throw new DataBaseException();
        }

    }
}