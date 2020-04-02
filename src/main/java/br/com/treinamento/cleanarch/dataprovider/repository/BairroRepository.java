package br.com.treinamento.cleanarch.dataprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinamento.cleanarch.dataprovider.entity.BairroTable;

public interface BairroRepository extends JpaRepository<BairroTable, Long> {

}