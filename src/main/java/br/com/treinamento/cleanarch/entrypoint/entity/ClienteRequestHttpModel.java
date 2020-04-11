package br.com.treinamento.cleanarch.entrypoint.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestHttpModel {

    @JsonAlias({"nome_cliente"})
    private String nomeCliente;

    @JsonAlias({"doc_cliente"})
    private Integer docCliente;

    @JsonAlias({"email_cliente"})
    private String emailCliente;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonAlias({"data_nacimento"})
    private LocalDate dataNascimento;
    
    @JsonAlias({"telefone_cliente"})
    private String telefoneCliente;

    private List<EnderecoRequestHttpModel> enderecos;
}