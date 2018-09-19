package br.com.cfc.gestor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.AulaProcessoVeiculo;

@Repository
public interface AulaRepository extends CrudRepository<AulaProcessoVeiculo, Long>{

}
