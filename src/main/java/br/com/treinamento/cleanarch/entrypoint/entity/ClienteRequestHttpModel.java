package br.com.treinamento.cleanarch.entrypoint.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestHttpModel {

    private String nomeCliente;
    private Integer docCliente;
    private String emailCliente;
    private String dataNascimento;
    private String telefoneCliente;
    private List<EnderecoRequestHttpModel> enderecos;
}