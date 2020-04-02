package br.com.treinamento.cleanarch.dataprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treinamento.cleanarch.dataprovider.entity.EstadoTable;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoTable, Long> {

}