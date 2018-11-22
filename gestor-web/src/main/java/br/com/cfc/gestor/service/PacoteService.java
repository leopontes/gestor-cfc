package br.com.cfc.gestor.service;

import br.com.cfc.gestor.model.Pacote;

public interface PacoteService {

	Iterable<Pacote> findAll();

	void save(Pacote pacote);

	Pacote get(Long id);

}
