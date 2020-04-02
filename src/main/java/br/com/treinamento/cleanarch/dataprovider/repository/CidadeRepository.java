package br.com.treinamento.cleanarch.dataprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinamento.cleanarch.dataprovider.entity.CidadeTable;

public interface CidadeRepository extends JpaRepository<CidadeTable, Long> {

}