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
//		Cria a lista que irá armazenar endereços que serão salvos
		List<EnderecoEntity> enderecos = new ArrayList<>();
		
        //		percorre os endereços que serão salvos
                for(EnderecoEntity enderecoNovo : entity.getEnderecos()){
        //			efetua busca de endereços cadastrados
                    List<EnderecoEntity> enderecosCad = enderecoGateway.buscarEndereco(enderecoNovo.getCep());
        //			verefica se existe retornou algum endereco
                    if( !enderecosCad.isEmpty() && enderecosCad.get(0).getId() > 0 ) {
        
                        EnderecoEntity endereco = enderecosCad.get(0);
        //				criou a entidade endereco que sera salva no banco de dados
                        EnderecoEntity enderecoEntity = EnderecoEntity.builder()
                                .logradouro(endereco.getLogradouro())
                                .numero(enderecoNovo.getNumero())
                                .cep(endereco.getCep())
                                .bairro(BairroEntity.builder()
                                        .id(endereco.getId())
                                        .nome(endereco.getBairro().getNome())
                                        .cidade(CidadeEntity.builder()
                                                .id(endereco.getId())
                                                .nome(endereco.getBairro().getCidade().getNome())
                                                .estado(EstadoEntity.builder()
                                                        .id(endereco.getId())
                                                        .nome(endereco.getBairro().getCidade().getEstado().getNome())
                                                        .build())
                                                .build())
                                        .build())
                                .build();
                        // Salva na lista o endereço reutilizando os dados de um endereço já cadastrado na base de dados
                        enderecos.add(enderecoEntity);
                    } else {
                        // Salva na lista o endereço que não está cadastrado na base de dados
                        enderecos.add(enderecoNovo);
                    }
                }
                
                entity.setEnderecos(enderecos);
                
                ClienteEntity clienteSalvo = clienteGateway.cadastrarCliente(entity);
                
                // forEach para salvar todos os endereços da lista, após salvar o cliente referente a cada endereço
                enderecos.forEach(endereco -> endereco = enderecoGateway.cadastrarEndereco(endereco));
                
                return clienteSalvo;
    }
}