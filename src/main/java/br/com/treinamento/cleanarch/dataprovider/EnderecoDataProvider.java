package br.com.treinamento.cleanarch.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinamento.cleanarch.configuration.exception.DataBaseException;
import br.com.treinamento.cleanarch.core.entity.EnderecoEntity;
import br.com.treinamento.cleanarch.core.gateway.EnderecoGateway;
import br.com.treinamento.cleanarch.dataprovider.entity.BairroTable;
import br.com.treinamento.cleanarch.dataprovider.entity.CidadeTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EnderecoTable;
import br.com.treinamento.cleanarch.dataprovider.entity.EstadoTable;
import br.com.treinamento.cleanarch.dataprovider.mapper.EnderecoTableMapper;
import br.com.treinamento.cleanarch.dataprovider.repository.BairroRepository;
import br.com.treinamento.cleanarch.dataprovider.repository.CidadeRepository;
import br.com.treinamento.cleanarch.dataprovider.repository.EnderecoRepository;
import br.com.treinamento.cleanarch.dataprovider.repository.EstadoRepository;

@Component
public class EnderecoDataProvider implements EnderecoGateway {
    
    @Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private BairroRepository bairroRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public EnderecoEntity cadastrarEndereco(EnderecoEntity entity) {
		try {			
			EnderecoTable enderecoTable = EnderecoTableMapper.to(entity);
			EstadoTable estadoTable = enderecoTable.getBairro().getCidade().getEstado();
			CidadeTable cidadeTable = enderecoTable.getBairro().getCidade();
			BairroTable bairroTable = enderecoTable.getBairro();
			
			//FIXME cadastrando as informações da tabela em sequência
			estadoTable = estadoRepository.save(estadoTable);
			cidadeTable = cidadeRepository.save(cidadeTable);
			bairroTable = bairroRepository.save(bairroTable);
			enderecoTable = enderecoRepository.save(enderecoTable);
			
			entity = EnderecoTableMapper.from(enderecoTable);
			
			return entity;
		} catch(Exception e) {
			throw new DataBaseException("Falha ao cadastrar endereço",e.getCause());
		}
	}

    public List<EnderecoEntity> buscarEndereco(Integer cep){
        try{
            List<EnderecoEntity> listaEntity = new ArrayList<>();
            List<EnderecoTable> listaTable = new ArrayList<>();

            listaTable = enderecoRepository.findByCep(cep);

            for(EnderecoTable endereco : listaTable){
                listaEntity.add(EnderecoTableMapper.from(endereco));
            }

            return listaEntity;
        }catch(Exception e){
            throw new DataBaseException("[E03] Falha ao buscar o endereço!");
        }

    }
}