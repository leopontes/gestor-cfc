package br.com.cfc.gestor.service;

import java.util.Collection;

import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.Processo;

public interface ProcessoService {

	void save(Processo processo);

	Collection<Processo> find(Aluno aluno);

}
