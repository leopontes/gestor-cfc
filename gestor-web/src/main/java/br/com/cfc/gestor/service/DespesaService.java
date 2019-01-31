package br.com.cfc.gestor.service;

import br.com.cfc.gestor.model.Despesa;

public interface DespesaService {

	Iterable<Despesa> findAll();

	void save(Despesa despesa);

	Despesa findById(Long id);

	void delete(Despesa despesa);

}
