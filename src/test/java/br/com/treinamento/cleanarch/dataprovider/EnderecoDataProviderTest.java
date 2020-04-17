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
import br.com.treinamento.cleanarch.core.entity.EnderecoEntity;
import br.com.treinamento.cleanarch.core.entity.EstadoEntity;
import br.com.treinamento.cleanarch.dataprovider.entity.BairroTable;
import br.com.treinamento.cleanarch.dataprovider.entity.CidadeTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EnderecoTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EstadoTable;
import br.com.treinamento.cleanarch.dataprovider.repository.BairroRepository;
import br.com.treinamento.cleanarch.dataprovider.repository.CidadeRepository;
import br.com.treinamento.cleanarch.dataprovider.repository.EnderecoRepository;
import br.com.treinamento.cleanarch.dataprovider.repository.EstadoRepository;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoDataProviderTest {

	@InjectMocks
	private EnderecoDataProvider enderecoDataProvider;
	
	@Mock
	private EnderecoRepository enderecoRepository;
	@Mock
	private BairroRepository bairroRepository;
	@Mock
	private CidadeRepository cidadeRepository;
	@Mock
	private EstadoRepository estadoRepository;
	
	@Test
	public void cadastrarEndereco_success() {
		
		EnderecoEntity enderecoEntity = EnderecoEntity.builder()
				.bairro(BairroEntity.builder()
						.cidade(CidadeEntity.builder()
								.estado(EstadoEntity.builder().build())
						.build())
				.build())
		.build();
		
		EnderecoTable enderecoTable = EnderecoTable.builder()
				.id(1L)
				.bairro(BairroTable.builder()
						.id(1L)
						.cidade(CidadeTable.builder()
								.id(1L)
								.estado(EstadoTable.builder().id(1L).build())
						.build())
				.build())
		.build();
		
		Mockito.when(estadoRepository.save(Mockito.any(EstadoTable.class))).thenReturn(new EstadoTable());
		Mockito.when(cidadeRepository.save(Mockito.any(CidadeTable.class))).thenReturn(new CidadeTable());
		Mockito.when(bairroRepository.save(Mockito.any(BairroTable.class))).thenReturn(new BairroTable());
		Mockito.when(enderecoRepository.save(Mockito.any(EnderecoTable.class))).thenReturn(enderecoTable);
		
		EnderecoEntity response = enderecoDataProvider.cadastrarEndereco(enderecoEntity);
		
		Assert.assertThat(response.getId(), Matchers.notNullValue());
	}
	
	@Test(expected = DataBaseException.class)
	public void cadastrarEndereco_exception() {
		
		EnderecoEntity endereco = EnderecoEntity.builder()
				.logradouro("endereco")
				.build();
		
		Mockito.when(enderecoDataProvider.cadastrarEndereco(endereco)).thenThrow(new DataBaseException("teste"));
		
		enderecoDataProvider.cadastrarEndereco(endereco);
	}
	
	@Test
	public void buscarEndereco_success() {
		List<EnderecoTable> listaTable = new ArrayList<>();
        listaTable.add(EnderecoTable.builder()
				.id(1L)
				.bairro(BairroTable.builder()
						.id(1L)
						.cidade(CidadeTable.builder()
								.id(1L)
								.estado(EstadoTable.builder().id(1L).build())
						.build())
				.build())
		.build());
        
        Mockito.when(enderecoRepository.findByCep(Mockito.anyInt())).thenReturn(listaTable);
        
        List<EnderecoEntity> listaResponse = enderecoDataProvider.buscarEndereco(123456);
        
        Assert.assertThat(listaResponse.size(), Matchers.notNullValue());
	}
	
	@Test(expected = DataBaseException.class)
	public void buscarEndereco_exception() {
		
		Mockito.when(enderecoRepository.findByCep(Mockito.anyInt())).thenThrow(new DataBaseException("teste"));
        
        enderecoDataProvider.buscarEndereco(123456);
        
	}
}
