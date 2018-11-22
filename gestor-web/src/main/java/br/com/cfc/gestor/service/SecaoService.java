package br.com.cfc.gestor.service;

import br.com.cfc.gestor.model.Secao;

public interface SecaoService {

	Iterable<Secao> findAll();

	void save(Secao secao);

}
