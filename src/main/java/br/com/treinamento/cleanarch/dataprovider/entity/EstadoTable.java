package br.com.treinamento.cleanarch.dataprovider.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "estado")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long id;

    @Column(name = "nm_estado")
    private String nome;

    @JsonBackReference
    @OneToMany(mappedBy = "estado")
    private List<CidadeTable> cidades;
}