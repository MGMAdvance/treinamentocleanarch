package br.com.treinamento.cleanarch.entrypoint;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.treinamento.cleanarch.core.entity.BairroEntity;
import br.com.treinamento.cleanarch.core.entity.CidadeEntity;
import br.com.treinamento.cleanarch.core.entity.ClienteEntity;
import br.com.treinamento.cleanarch.core.entity.EnderecoEntity;
import br.com.treinamento.cleanarch.core.entity.EstadoEntity;
import br.com.treinamento.cleanarch.core.usecase.CadastrarClienteUseCase;
import br.com.treinamento.cleanarch.entrypoint.entity.ClienteRequestHttpModel;
import br.com.treinamento.cleanarch.entrypoint.entity.EnderecoRequestHttpModel;

@RunWith(MockitoJUnitRunner.class)
public class ClienteEntrypointTest {
	
	private MockMvc mockMvc;

	@InjectMocks
	private ClienteEntrypoint clienteEntrypoint;
	
	@Mock
	private CadastrarClienteUseCase clienteUseCase;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.clienteEntrypoint).build();
	}
	
	@Test
	public void cadastrarCliente_status201() throws JsonProcessingException, Exception {
		
		List<EnderecoRequestHttpModel> enderecos = new ArrayList<>();
		enderecos.add(EnderecoRequestHttpModel.builder().build());
		
		ClienteRequestHttpModel clienteHttpModel = ClienteRequestHttpModel.builder()
			.nomeCliente("Matheus")
			.enderecos(enderecos)
			.build();
		
		List<EnderecoEntity> enderecosEntity = new ArrayList<>();
		enderecosEntity.add(EnderecoEntity.builder()
				.id(1L)
				.bairro(BairroEntity.builder()
						.id(1L)
						.cidade(CidadeEntity.builder()
								.estado(EstadoEntity.builder().id(1L).build())
						.build())
				.build())
		.build());
		
		ClienteEntity clienteEntity = ClienteEntity.builder()
			.id(1L)
			.nome("Matheus")
			.enderecos(enderecosEntity)
			.build();

		Mockito.when(clienteUseCase.cadastrarCliente(Mockito.any(ClienteEntity.class))).thenReturn(clienteEntity);
		
		this.mockMvc.perform(
			MockMvcRequestBuilders
			.post("/cliente/")
			.contentType(MediaType.APPLICATION_JSON)
			.content( new ObjectMapper().writeValueAsString(clienteHttpModel) )
			).andExpect(status().isCreated());
		
	}
}