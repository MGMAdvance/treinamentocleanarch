package br.com.treinamento.cleanarch.entrypoint.mapper;

import java.util.Optional;

import br.com.treinamento.cleanarch.core.entity.BairroEntity;
import br.com.treinamento.cleanarch.core.entity.CidadeEntity;
import br.com.treinamento.cleanarch.core.entity.EnderecoEntity;
import br.com.treinamento.cleanarch.core.entity.EstadoEntity;
import br.com.treinamento.cleanarch.entrypoint.entity.EnderecoRequestHttpModel;

public class EnderecoRequestHttpModelMapper {
    public static EnderecoRequestHttpModel to(EnderecoEntity entity){
        return Optional.ofNullable(entity).map(endereco -> 
            EnderecoRequestHttpModel.builder()
                .logradouro(endereco.getLogradouro())
                .bairro(endereco.getBairro().getNome())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .cidade(endereco.getBairro().getCidade().getNome())
                .estado(endereco.getBairro().getCidade().getEstado().getNome())
            .build())
            .orElse(EnderecoRequestHttpModel.builder().build());
    }

    public static EnderecoEntity from(EnderecoRequestHttpModel httpModel){
        return Optional.ofNullable(httpModel).map(endereco -> 
            EnderecoEntity.builder()
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .bairro(
                    BairroEntity.builder()
                        .nome(endereco.getBairro())
                        .cidade(
                            CidadeEntity.builder()
                                .nome(endereco.getCidade())
                                .estado(
                                    EstadoEntity.builder()
                                        .nome(endereco.getEstado())
                                    .build())
                            .build())
                    .build())
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