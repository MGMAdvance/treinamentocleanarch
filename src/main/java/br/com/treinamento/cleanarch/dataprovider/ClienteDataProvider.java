package br.com.treinamento.cleanarch.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinamento.cleanarch.dataprovider.repository.ClienteRepository;

@Component
public class ClienteDataProvider {
    
    @Autowired
    private ClienteRepository clienteRepository;

    // public ClienteEntity cadastrarCliente(ClienteEntity entity){
    //     return null;
    // }

    // public ClienteEntity buscarCliente(Long id){
    //     return null;
    // }
}