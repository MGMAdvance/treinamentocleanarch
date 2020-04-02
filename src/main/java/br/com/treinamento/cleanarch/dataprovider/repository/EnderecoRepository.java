package br.com.treinamento.cleanarch.dataprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinamento.cleanarch.dataprovider.entity.EnderecoTable;

public interface EnderecoRepository extends JpaRepository<EnderecoTable, Long> {

}