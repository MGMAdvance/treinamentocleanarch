package br.com.treinamento.cleanarch.core.usecase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinamento.cleanarch.core.entity.BairroEntity;
import br.com.treinamento.cleanarch.core.entity.CidadeEntity;
import br.com.treinamento.cleanarch.core.entity.ClienteEntity;
import br.com.treinamento.cleanarch.core.entity.EnderecoEntity;
import br.com.treinamento.cleanarch.core.entity.EstadoEntity;
import br.com.treinamento.cleanarch.core.gateway.ClienteGateway;
import br.com.treinamento.cleanarch.core.gateway.EnderecoGateway;

@Service
public class CadastrarClienteUseCase {

    @Autowired
    private ClienteGateway clienteGateway;

    @Autowired
    private EnderecoGateway enderecoGateway;

    public ClienteEntity cadastrarCliente(ClienteEntity entity){
        List<EnderecoEntity> enderecos = new ArrayList<>();

        for(EnderecoEntity enderecoNovo : entity.getEnderecos()){
            List<EnderecoEntity> enderecosCadastro = enderecoGateway.buscarEndereco(enderecoNovo.getCep());

            if(enderecosCadastro.get(0).getId() > 0){
                EnderecoEntity endereco = enderecosCadastro.get(0);

                EnderecoEntity enderecoEntity = EnderecoEntity.builder()
                    .logradouro(endereco.getLogradouro())
                    .numero(enderecoNovo.getNumero())
                    .cep(endereco.getCep())
                    .bairro(BairroEntity.builder()
                            .id(endereco.getId())
                            .cidade(CidadeEntity.builder()
                                    .id(endereco.getId())
                                    .estado(EstadoEntity.builder()
                                            .id(endereco.getId())
                                            .build())
                                    .build())
                            .build())
                    .build();

                    enderecoEntity = enderecoGateway.cadastrarEndereco(enderecoEntity);

                    enderecos.add(enderecoEntity);
            } else {
                EnderecoEntity enderecoEntity = enderecoGateway.cadastrarEndereco(enderecoNovo);

                enderecos.add(enderecoEntity);
            }
        }

        entity.setEnderecos(enderecos);
		
		ClienteEntity clienteSalvo = clienteGateway.cadastrarCliente(entity);
		return clienteSalvo;
    }
}