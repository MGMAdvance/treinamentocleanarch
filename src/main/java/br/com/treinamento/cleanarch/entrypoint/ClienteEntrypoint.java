package br.com.treinamento.cleanarch.entrypoint;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.treinamento.cleanarch.core.entity.ClienteEntity;
import br.com.treinamento.cleanarch.core.usecase.CadastrarClienteUseCase;
import br.com.treinamento.cleanarch.entrypoint.mapper.ClienteRequestHttpModelMapper;
import br.com.treinamento.cleanarch.entrypoint.entity.ClienteRequestHttpModel;

@Controller
@RequestMapping("/cliente")
public class ClienteEntrypoint {
    
    @Autowired
    private CadastrarClienteUseCase cadastrarClienteUseCase;

    @PostMapping("/")
    public ResponseEntity<?> cadastrarCliente(@RequestBody ClienteRequestHttpModel requestHttpModel){
        ClienteEntity entityCadastro = ClienteRequestHttpModelMapper.from(requestHttpModel);
        ClienteEntity entity = cadastrarClienteUseCase.cadastrarCliente(entityCadastro);

        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
                    
        return ResponseEntity.created(uri).build();
    } 
}