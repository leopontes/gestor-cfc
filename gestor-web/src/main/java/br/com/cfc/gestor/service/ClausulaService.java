package br.com.cfc.gestor.service;

import java.util.Optional;

import br.com.cfc.gestor.model.Clausula;
import br.com.cfc.gestor.model.Contrato;

public interface ClausulaService {

	Optional<Clausula> get(Long idClausula);

	void save(Clausula clausula);

	Iterable<Clausula> findBy(Contrato contrato);


}
