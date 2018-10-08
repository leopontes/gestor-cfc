package br.com.cfc.gestor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cfc.gestor.model.Clausula;
import br.com.cfc.gestor.model.Contrato;

@Repository
public interface ClausulaRepository extends CrudRepository<Clausula, Long>{

	Iterable<Clausula> findByContrato(Contrato contrato);

}
