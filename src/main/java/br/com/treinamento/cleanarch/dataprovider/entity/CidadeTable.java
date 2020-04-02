package br.com.treinamento.cleanarch.dataprovider.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "cidade")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CidadeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade")
    private Long id;

    @Column(name = "nm_cidade")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private EstadoTable estado;

    @JsonBackReference
    @OneToMany(mappedBy = "cidade")
    private List<BairroTable> bairros;
}