package br.com.treinamento.cleanarch.dataprovider.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.treinamento.cleanarch.core.entity.ClienteEntity;
import br.com.treinamento.cleanarch.core.entity.EnderecoEntity;
import br.com.treinamento.cleanarch.dataprovider.entity.ClienteTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EnderecoTable;

public class ClienteTableMapper {
    public static ClienteTable to(ClienteEntity entity){
        return Optional.ofNullable(entity).map(cliente -> ClienteTable.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .telefone(cliente.getTelefone())
                .enderecos(toEnderecos(cliente.getEnderecos()))
                .documento(cliente.getDocumento())
                .email(cliente.getEmail())
                .dataNascimento(cliente.getDataNascimento())
            .build())
            .orElse(ClienteTable.builder().build());
    }

    private static List<EnderecoTable> toEnderecos(List<EnderecoEntity> enderecos){
        List<EnderecoTable> listaEnderecos = new ArrayList<>();

        for(EnderecoEntity endereco : enderecos){
            listaEnderecos.add(EnderecoTableMapper.to(endereco));
        }

        return listaEnderecos;
    }

    /**
     
        FROM

     */

    public static ClienteEntity from(ClienteTable table){
        return Optional.ofNullable(table).map(cliente -> ClienteEntity.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .telefone(cliente.getTelefone())
                .enderecos(fromEnderecos(cliente.getEnderecos()))
                .documento(cliente.getDocumento())
                .email(cliente.getEmail())
                .dataNascimento(cliente.getDataNascimento())
            .build())
            .orElse(ClienteEntity.builder().build());
    }

    private static List<EnderecoEntity> fromEnderecos(List<EnderecoTable> enderecos){
        List<EnderecoEntity> listaEnderecos = new ArrayList<>();

        for(EnderecoTable endereco : enderecos){
            listaEnderecos.add(EnderecoTableMapper.from(endereco));
        }

        return listaEnderecos;
    }
}