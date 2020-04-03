package br.com.treinamento.cleanarch.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BairroEntity {
    
    private Long id;

    private String nome;

    private CidadeEntity cidade;
}