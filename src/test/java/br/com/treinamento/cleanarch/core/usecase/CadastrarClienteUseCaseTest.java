package br.com.treinamento.cleanarch.core.usecase;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.treinamento.cleanarch.core.entity.BairroEntity;
import br.com.treinamento.cleanarch.core.entity.CidadeEntity;
import br.com.treinamento.cleanarch.core.entity.ClienteEntity;
import br.com.treinamento.cleanarch.core.entity.EnderecoEntity;
import br.com.treinamento.cleanarch.core.entity.EstadoEntity;
import br.com.treinamento.cleanarch.dataprovider.ClienteDataProvider;
import br.com.treinamento.cleanarch.dataprovider.EnderecoDataProvider;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarClienteUseCaseTest {

	@InjectMocks
	private CadastrarClienteUseCase clienteUseCase;
	
	@Mock
    private ClienteDataProvider clienteDataprovider;

    @Mock
    private EnderecoDataProvider enderecoDataprovider;
    
    @Test
    public void cadastrarClienteUseCase_success_with_feign() {
    	
    	List<EnderecoEntity> enderecosFeign = new ArrayList<>();
    	enderecosFeign.add(EnderecoEntity.builder()
    			.id(1L)
    			.cep(111)
				.bairro(BairroEntity.builder()
						.id(1L)
						.cidade(CidadeEntity.builder()
								.id(1L)
								.estado(EstadoEntity.builder().id(1L).build())
						.build())
				.build())
		.build());
    	
    	
    	List<EnderecoEntity> enderecosSalvo = new ArrayList<>();
    	enderecosSalvo.add(EnderecoEntity.builder()
    			.id(1L)
    			.cep(111)
				.bairro(BairroEntity.builder()
						.id(1L)
						.cidade(CidadeEntity.builder()
								.id(1L)
								.estado(EstadoEntity.builder().id(1L).build())
						.build())
				.build())
		.build());
    	
    	List<EnderecoEntity> enderecosNovo = new ArrayList<>();
    	enderecosNovo.add(EnderecoEntity.builder()
    			.cep(111)
				.bairro(BairroEntity.builder()
						.cidade(CidadeEntity.builder()
								.estado(EstadoEntity.builder().build())
						.build())
				.build())
		.build());
    	
    	ClienteEntity clienteTeste = ClienteEntity.builder()
    			.nome("Ricardo")
    			.enderecos(enderecosNovo)
    			.build();
    	
    	ClienteEntity clienteSalvo = ClienteEntity.builder()
    			.id(1L)
    			.nome("Ricardo")
    			.enderecos(enderecosSalvo)
    			.build();
    	
    	Mockito.when(enderecoDataprovider.buscarEndereco(Mockito.anyInt())).thenReturn(enderecosFeign);
    	Mockito.when(enderecoDataprovider.cadastrarEndereco(Mockito.any(EnderecoEntity.class))).thenReturn(enderecosSalvo.get(0));
    	Mockito.when(clienteDataprovider.cadastrarCliente(Mockito.any(ClienteEntity.class))).thenReturn(clienteSalvo);
    	
    	ClienteEntity response = clienteUseCase.cadastrarCliente(clienteTeste);
    	
    	Assert.assertThat(response.getId(), Matchers.notNullValue());
    }
    
    @Test
    public void cadastrarClienteUseCase_success_empty_feign() {
    	
    	List<EnderecoEntity> enderecosSalvo = new ArrayList<>();
    	enderecosSalvo.add(EnderecoEntity.builder()
    			.id(1L)
    			.cep(111)
				.bairro(BairroEntity.builder()
						.id(1L)
						.cidade(CidadeEntity.builder()
								.id(1L)
								.estado(EstadoEntity.builder().id(1L).build())
						.build())
				.build())
		.build());
    	
    	List<EnderecoEntity> enderecosNovo = new ArrayList<>();
    	enderecosNovo.add(EnderecoEntity.builder()
    			.cep(111)
				.bairro(BairroEntity.builder()
						.cidade(CidadeEntity.builder()
								.estado(EstadoEntity.builder().build())
						.build())
				.build())
		.build());
    	
    	ClienteEntity clienteTeste = ClienteEntity.builder()
    			.nome("Ricardo")
    			.enderecos(enderecosNovo)
    			.build();
    	
    	ClienteEntity clienteSalvo = ClienteEntity.builder()
    			.id(1L)
    			.nome("Ricardo")
    			.enderecos(enderecosSalvo)
    			.build();
    	
    	Mockito.when(enderecoDataprovider.buscarEndereco(Mockito.anyInt())).thenReturn(new ArrayList<>());
    	Mockito.when(enderecoDataprovider.cadastrarEndereco(Mockito.any(EnderecoEntity.class))).thenReturn(enderecosNovo.get(0));
    	Mockito.when(clienteDataprovider.cadastrarCliente(Mockito.any(ClienteEntity.class))).thenReturn(clienteSalvo);
    	
    	ClienteEntity response = clienteUseCase.cadastrarCliente(clienteTeste);
    	
    	Assert.assertThat(response.getId(), Matchers.notNullValue());
    }
}
