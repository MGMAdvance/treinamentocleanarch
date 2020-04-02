package br.com.treinamento.cleanarch.dataprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinamento.cleanarch.dataprovider.entity.ClienteTable;

public interface ClienteRepository extends JpaRepository<ClienteTable, Long> {

}