package br.com.treinamento.cleanarch.core.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {
    
    private Long id;

    private String nome;

    private String telefone;

    private List<EnderecoEntity> enderecos;

    private Integer documento;

    private String email;

    private LocalDate dataNascimento;
}