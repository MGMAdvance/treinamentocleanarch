package br.com.treinamento.cleanarch.entrypoint.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestHttpModel {

    private String logradouro;
    private String bairro;
    private String numero;
    private Integer cep;
    private String cidade;
    private String estado;

}