package br.com.cfc.gestor.service;

import br.com.cfc.gestor.model.Clausula;
import br.com.cfc.gestor.model.Paragrafo;

public interface ParagrafoService {

	Iterable<Paragrafo> findAll(Clausula clausula);

	void save(Paragrafo paragrafo);

}
