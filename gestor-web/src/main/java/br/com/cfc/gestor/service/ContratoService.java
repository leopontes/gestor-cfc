package br.com.cfc.gestor.service;

import java.util.Optional;

import br.com.cfc.gestor.model.Contrato;

public interface ContratoService {

	Optional<Contrato> get(Long id);

	Iterable<Contrato> findAll();

	void save(Contrato contrato);

}
