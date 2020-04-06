package br.com.treinamento.cleanarch.entrypoint.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.treinamento.cleanarch.core.entity.ClienteEntity;
import br.com.treinamento.cleanarch.core.entity.EnderecoEntity;
import br.com.treinamento.cleanarch.entrypoint.entity.ClienteRequestHttpModel;
import br.com.treinamento.cleanarch.entrypoint.entity.EnderecoRequestHttpModel;

public class ClienteRequestHttpModelMapper {
    public static ClienteRequestHttpModel to(ClienteEntity entity){
        return Optional.ofNullable(entity).map(cliente -> ClienteRequestHttpModel.builder()
                .nomeCliente(cliente.getNome())
                .telefoneCliente(cliente.getTelefone())
                .enderecos(toEnderecos(cliente.getEnderecos()))
                .docCliente(cliente.getDocumento())
                .emailCliente(cliente.getEmail())
                .dataNascimento(cliente.getDataNascimento())
            .build())
            .orElse(ClienteRequestHttpModel.builder().build());
    }

    private static List<EnderecoRequestHttpModel> toEnderecos(List<EnderecoEntity> enderecosCliente){
        List<EnderecoRequestHttpModel> listaEnderecos = new ArrayList<>();

        for(EnderecoEntity endereco : enderecosCliente){
            listaEnderecos.add(EnderecoRequestHttpModelMapper.to(endereco));
        }

        return listaEnderecos;
    }

    public static ClienteEntity from(ClienteRequestHttpModel httpModel){
        return Optional.ofNullable(httpModel).map(cliente -> 
            ClienteEntity.builder()
                .nome(cliente.getNomeCliente())
                .telefone(cliente.getTelefoneCliente())
                .enderecos(fromEnderecos(cliente.getEnderecos()))
                .documento(cliente.getDocCliente())
                .email(cliente.getEmailCliente())
                .dataNascimento(cliente.getDataNascimento())
            .build())
            .orElse(ClienteEntity.builder().build());
    }

    private static List<EnderecoEntity> fromEnderecos(List<EnderecoRequestHttpModel> enderecosCliente){
        List<EnderecoEntity> listaEnderecos = new ArrayList<>();

        for(EnderecoRequestHttpModel endereco : enderecosCliente){
            listaEnderecos.add(EnderecoRequestHttpModelMapper.from(endereco));
        }

        return listaEnderecos;
    }
}