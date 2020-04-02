package br.com.treinamento.cleanarch.dataprovider.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "cliente")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nm_cliente")
    private String nome;

    @Column(name = "ds_telefone")
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<EnderecoTable> enderecos;

    @Column(name = "ds_documento")
    private Integer documento;

    @Column(name = "ds_email")
    private String email;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;
}