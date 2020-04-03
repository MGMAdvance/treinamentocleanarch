package br.com.treinamento.cleanarch.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CidadeEntity {
    private Long id;

    private String nome;

    private EstadoEntity estado;
}