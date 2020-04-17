package br.com.treinamento.cleanarch.dataprovider;

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

import br.com.treinamento.cleanarch.configuration.exception.DataBaseException;
import br.com.treinamento.cleanarch.core.entity.BairroEntity;
import br.com.treinamento.cleanarch.core.entity.CidadeEntity;
import br.com.treinamento.cleanarch.core.entity.ClienteEntity;
import br.com.treinamento.cleanarch.core.entity.EnderecoEntity;
import br.com.treinamento.cleanarch.core.entity.EstadoEntity;
import br.com.treinamento.cleanarch.dataprovider.entity.BairroTable;
import br.com.treinamento.cleanarch.dataprovider.entity.CidadeTable;
import br.com.treinamento.cleanarch.dataprovider.entity.ClienteTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EnderecoTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EstadoTable;
import br.com.treinamento.cleanarch.dataprovider.repository.ClienteRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClienteDataProviderTest {

	@InjectMocks
	private ClienteDataProvider clienteDataProvider;
	
	@Mock
	private ClienteRepository clienteRepository;
	
	@Test
	public void cadastrarCliente_success() {
		// Lista de enderecos que serão mapeadas atráves do entity
		List<EnderecoTable> enderecos = new ArrayList<>();
		enderecos.add(EnderecoTable.builder()
				.bairro(BairroTable.builder()
						.cidade(CidadeTable.builder()
								.estado(EstadoTable.builder().build())
						.build())
				.build())
		.build());
		
		// Lista de endereços que deve ser retornada após o repository.save
		List<EnderecoTable> enderecosSalvos = new ArrayList<>();
		enderecosSalvos.add(EnderecoTable.builder()
				.id(1L)
				.bairro(BairroTable.builder()
						.id(1L)
						.cidade(CidadeTable.builder()
								.id(1L)
								.estado(EstadoTable.builder()
										.id(1L)
								.build())
						.build())
				.build())
		.build());
		
		
		ClienteTable cliente = ClienteTable.builder()
				.nome("Victor")
				.enderecos(enderecos)
				.build();
		
		ClienteTable clienteSalvo = ClienteTable.builder()
				.id(1L)
				.enderecos(enderecosSalvos)
				.nome("Victor")
				.build();
		
		// Lista de endereços que será associada ao clienteEntity
		List<EnderecoEntity> enderecosEntity = new ArrayList<>();
		enderecosEntity.add(EnderecoEntity.builder()
				.bairro(BairroEntity.builder()
						.cidade(CidadeEntity.builder()
								.estado(EstadoEntity.builder().build())
						.build())
				.build())
		.build());
		
		ClienteEntity entity = ClienteEntity.builder()
				.nome("Victor")
				.enderecos(enderecosEntity)
				.build();
		
		Mockito.when(clienteRepository.save(cliente)).thenReturn(clienteSalvo);
		
		ClienteEntity response = clienteDataProvider.cadastrarCliente(entity);
		
		Assert.assertThat(response.getId(), Matchers.notNullValue());
	}
	
	@Test(expected = DataBaseException.class)
	public void cadastrarCliente_dataBaseException() {
		
		// Enviando um cliente que não possui uma lista de endereços, para forçar a exception
		ClienteEntity entity = ClienteEntity.builder()
				.nome("Victor")
				.build();
		
		clienteDataProvider.cadastrarCliente(entity);
	}
}
