package br.com.treinamento.cleanarch.dataprovider.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "endereco")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    @Column(name = "nm_logradouro")
    private String logradouro;

    @Column(name = "nr_endereco")
    private String numero;

    @Column(name = "nr_cep")
    private Integer cep;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private BairroTable bairro;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteTable cliente;
}