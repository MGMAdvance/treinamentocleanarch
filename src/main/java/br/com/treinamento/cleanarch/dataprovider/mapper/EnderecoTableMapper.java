package br.com.treinamento.cleanarch.dataprovider.mapper;

import java.util.Optional;

import br.com.treinamento.cleanarch.core.entity.BairroEntity;
import br.com.treinamento.cleanarch.core.entity.CidadeEntity;
import br.com.treinamento.cleanarch.core.entity.EnderecoEntity;
import br.com.treinamento.cleanarch.core.entity.EstadoEntity;
import br.com.treinamento.cleanarch.dataprovider.entity.BairroTable;
import br.com.treinamento.cleanarch.dataprovider.entity.CidadeTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EnderecoTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EstadoTable;

public class EnderecoTableMapper {
    public static EnderecoTable to(EnderecoEntity entity){
        return Optional.ofNullable(entity).map(endereco -> 
            EnderecoTable.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .bairro(Optional.ofNullable(endereco.getBairro()).map(bairro -> 
                    BairroTable.builder()
                        .id(bairro.getId())
                        .nome(bairro.getNome())
                        .cidade(Optional.ofNullable(bairro.getCidade()).map(cidade ->
                            CidadeTable.builder()
                                .id(cidade.getId())
                                .nome(cidade.getNome())
                                .estado(Optional.ofNullable(cidade.getEstado()).map(estado ->
                                    EstadoTable.builder()
                                        .id(estado.getId())
                                        .nome(estado.getNome())
                                    .build())
                                    .orElse(EstadoTable.builder().build()))
                            .build())
                            .orElse(CidadeTable.builder()
                                    .estado(EstadoTable.builder().build())
                                .build()))
                    .build())
                    .orElse(BairroTable.builder()
                            .cidade(CidadeTable.builder()
                                    .estado(EstadoTable.builder().build())
                                .build())
                        .build()))
            .build())
            .orElse(EnderecoTable.builder()
                    .bairro(BairroTable.builder()
                        .cidade(CidadeTable.builder()
                            .estado(EstadoTable.builder().build())
                        .build())
                    .build())
                .build());
    }

    public static EnderecoEntity from(EnderecoTable table){
        return Optional.ofNullable(table).map(endereco -> 
            EnderecoEntity.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .bairro(Optional.ofNullable(endereco.getBairro()).map(bairro -> 
                    BairroEntity.builder()
                        .id(bairro.getId())
                        .nome(bairro.getNome())
                        .cidade(Optional.ofNullable(bairro.getCidade()).map(cidade ->
                            CidadeEntity.builder()
                                .id(cidade.getId())
                                .nome(cidade.getNome())
                                .estado(Optional.ofNullable(cidade.getEstado()).map(estado ->
                                    EstadoEntity.builder()
                                        .id(estado.getId())
                                        .nome(estado.getNome())
                                    .build())
                                    .orElse(EstadoEntity.builder().build()))
                            .build())
                            .orElse(CidadeEntity.builder()
                                    .estado(EstadoEntity.builder().build())
                                .build()))
                    .build())
                    .orElse(BairroEntity.builder()
                            .cidade(CidadeEntity.builder()
                                    .estado(EstadoEntity.builder().build())
                                .build())
                        .build()))
            .build())
            .orElse(EnderecoEntity.builder()
                    .bairro(BairroEntity.builder()
                        .cidade(CidadeEntity.builder()
                            .estado(EstadoEntity.builder().build())
                        .build())
                    .build())
                .build());
    }
}